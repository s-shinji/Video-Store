package com.example.sampleproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
// import java.util.Optional;

// import com.example.sampleproject.entity.DbUserDetails;
import com.example.sampleproject.entity.Movie;
import com.example.sampleproject.entity.Review;
import com.example.sampleproject.service.MovieService;
import com.example.sampleproject.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//springとreactでurlがかぶらないようにするため追加
// @RequestMapping("/KdiJ362")
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	//依存関係？
	@Autowired
	MovieService movieService;
	@Autowired
	Movie movie;

	@RequestMapping(value="/review/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object review(Review review, @PathVariable("id") int movieId
									  , @RequestParam("review") String reviewString
									  , @RequestParam int loginUserId) {
		// reviewのhiddenが不正に操作された場合の処理
		// if(!("good".equals(reviewString) || "normal".equals(reviewString) || "bad".equals(reviewString))) {
		// 	return "エラー：レビューの値が不正です";
		// }

		review.setReview(reviewString);
		review.setMovie_id(movieId);

		review.setUser_id(loginUserId);

		//投稿者が自らの動画にReviewをするためにhiddenタグを不正に操作された場合の処理
		//依存関係？
		// Optional<Movie> movieOpt = movieService.getUserIdByMovieId(movieId);
		// Movie movie              = new Movie();
		// if(movieOpt.isPresent()) {
		// 	movie = movieOpt.get();
		// }
		// if(movie.getUserId() == loginUserId) {
		// 	return "エラー：ログイン中のユーザーと動画の投稿者が同じです。";
		// }


		//同ユーザーがReviewを複数回した場合以前の分を削除する
		reviewService.deleteReview(movieId,loginUserId);
		//新たなReviewを登録する
		reviewService.insertReview(review);
		
		//Reviewの値を取得する
        List<Review> reviewLists          = reviewService.findReviewById(movieId);
        Map<String,Integer> reviewMap     = new HashMap<String, Integer>();
        reviewMap.put("good", 0);
        reviewMap.put("normal", 0);
		reviewMap.put("bad", 0);
        for(Review reviewList : reviewLists) {
            if("good".equals(reviewList.getReview())) {
                reviewMap.put("good", reviewMap.get("good") + 1 );
            } else if("normal".equals(reviewList.getReview())) {
                reviewMap.put("normal", reviewMap.get("normal") + 1 );
            } else if("bad".equals(reviewList.getReview())) {
                reviewMap.put("bad", reviewMap.get("bad") + 1 );
			}
		}
        return reviewMap;

	}
}