package com.codebase.backend.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codebase.backend.review.dto.ReviewComment;
import com.codebase.backend.review.service.ReviewCommentService;

@Controller
@RequestMapping("/api/review/comment")
public class ReviewCommentController {
	
	@Autowired
	private ReviewCommentService reviewCommentService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addComment(@RequestBody ReviewComment comment) {
		try {
			reviewCommentService.addComment(comment); //서비스 호출
			return ResponseEntity.ok("댓글이 성공적으로 저장되었습니다.");
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 저장 실패");
		}
	}
	
	@GetMapping("/{reviewId}")
	public List<ReviewComment> getComments(@PathVariable int reviewId){
		return reviewCommentService.getCommentsByReviewId(reviewId);
	}

}
