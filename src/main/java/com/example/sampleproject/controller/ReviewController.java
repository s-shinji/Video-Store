package com.example.sampleproject.controller;

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
		if(!("good".equals(reviewString) || "normal".equals(reviewString) || "bad".equals(reviewString))) {
			model.addAttribute("error2", "いいねの値が不正です");
			return "errorMessage";
		}

		review.setReview(reviewString);
		review.setMovie_id(movie_id);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if(authentication.getPrincipal() instanceof DbUserDetails){
			int userId = ((DbUserDetails)authentication.getPrincipal()).getUserId();
			review.setUser_id(userId);

			//投稿者が自らの動画にReviewをするためにhiddenタグを不正に操作した場合の処理
			//依存関係？
			Optional<Movie> movieOpt = movieService.getUserIdByMovieId(movie_id);
			Movie movie = new Movie();
			if(movieOpt.isPresent()) {
				movie = movieOpt.get();
			}
			if(movie.getUserId() == userId) {
				model.addAttribute("error3", "ログイン中のユーザーと動画の投稿者が同じです。");
				return "errorMessage";
			}

			//同ユーザーがReviewを複数回した場合以前の分を削除する
			reviewService.deleteReview(movie_id,userId);

		}	
		
		reviewService.insertReview(review);
		return "redirect:/video/" + movie_id;
	}
}