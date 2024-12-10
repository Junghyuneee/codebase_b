package com.codebase.backend.review.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codebase.backend.review.dto.ReviewComment;
import com.codebase.backend.review.mapper.ReviewCommentMapper;

@Service
public class ReviewCommentService {
	
	@Autowired
	private ReviewCommentMapper reviewCommentMapper;
	
	public void addComment(ReviewComment reviewComment) {
		reviewCommentMapper.addComment(reviewComment);
	}
	
	public List<ReviewComment> getCommentsByReviewId(int reviewId){
		return reviewCommentMapper.getCommentsByReviewId(reviewId);
	}

}
