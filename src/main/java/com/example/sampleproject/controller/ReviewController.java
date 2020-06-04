package com.example.sampleproject.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.sampleproject.entity.DbUserDetails;
import com.example.sampleproject.entity.Movie;
import com.example.sampleproject.entity.Review;
import com.example.sampleproject.service.MovieService;
import com.example.sampleproject.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	//依存関係？
	@Autowired
	MovieService movieService;
	// 後々session変数に変換するなどして不正な値を検知する（今はhiddenで受け取る）
	// movie_idやreviewが偽装された場合にどのようにして前のページに戻すか
	//同じユーザーが再度reviewのリンクを押した場合の処理
	@PostMapping("/review/{id}")
	public String review(Review review, @PathVariable("id") int movie_id, @RequestParam("review") String reviewString,Model model) {
		//reviewのhiddenが不正に操作された場合の処理
		if(!("good".equals(reviewString) || "normal".equals(reviewString) || "bad".equals(reviewString))) {
			model.addAttribute("error2", "エラー：レビューの値が不正です");
			return "errorMessage";
		}

		review.setReview(reviewString);
		review.setMovie_id(movie_id);

		//ログインユーザー取得
		int userId = 0;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if(authentication.getPrincipal() instanceof DbUserDetails){
			userId = ((DbUserDetails)authentication.getPrincipal()).getUserId();
			review.setUser_id(userId);

			//投稿者が自らの動画にReviewをするためにhiddenタグを不正に操作された場合の処理
			//依存関係？
			Optional<Movie> movieOpt = movieService.getUserIdByMovieId(movie_id);
			Movie movie = new Movie();
			if(movieOpt.isPresent()) {
				movie = movieOpt.get();
			}
			if(movie.getUserId() == userId) {
				model.addAttribute("error3", "エラー：ログイン中のユーザーと動画の投稿者が同じです。");
				return "errorMessage";
			}

			//同ユーザーがReviewを複数回した場合以前の分を削除する
			reviewService.deleteReview(movie_id,userId);

		}	
		reviewService.insertReview(review);


		//ここから下はSampelcontrollerのGetMapping("video/{id}")とほぼ同様の処理（return"detail"を実現するため）（再生回数は増やさない）
		Optional<Movie> movieOpt2 = movieService.getMovie(movie_id);
		Movie movie2 = new Movie();
        if(movieOpt2.isPresent()) {
           movie2 = movieOpt2.get();
        }
        StringBuffer data = new StringBuffer();
        String base64_3 = Base64.getEncoder().encodeToString(movie2.getMovie());
        data.append("data:video/mp4;base64,");
        data.append(base64_3);
        model.addAttribute("convert", data.toString());
		model.addAttribute("movie", movie2);
		//既にReview済みの動画かどうかを判断するための処理
		String matchReview = "";
		if(reviewService.findMatchUserId(movie_id,userId) == null) {
		} else {
			matchReview = reviewService.findMatchUserId(movie_id,userId);
		}
		model.addAttribute("matchReview", matchReview);
		//Reviewの値を取得する
        List<Review> review2 = reviewService.findReviewById(movie_id);
        Map<String,Integer> reviewMap = new HashMap<String, Integer>();
        reviewMap.put("good", 0);
        reviewMap.put("normal", 0);
        reviewMap.put("bad", 0);
        for(int i = 0; i < review2.size(); i++) {
            if("good".equals(review2.get(i).getReview())) {
                reviewMap.put("good", reviewMap.get("good") + 1 );
            } else if("normal".equals(review2.get(i).getReview())) {
                reviewMap.put("normal", reviewMap.get("normal") + 1 );
            } else if("bad".equals(review2.get(i).getReview())) {
                reviewMap.put("bad", reviewMap.get("bad") + 1 );
            }
        }
        model.addAttribute("reviewMap", reviewMap);


		return "detail";
	}
}