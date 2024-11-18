package com.codebase.backend.review.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codebase.backend.review.dto.Review;
import com.codebase.backend.review.mapper.ReviewMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
	
	
	private final ReviewMapper reviewMapper;
	
	/*
	 * public ReviewService(Review review) { this.reviewMapper = reviewMapper; }
	 */

	
	// 리뷰 등록
	public void createReview(Review review) {
		reviewMapper.insertReview(review);
	}
	
	// 모든 리뷰 목록 조회
	public List<Review> selectAllReviews(){
		return reviewMapper.selectAllReviews();
	}
	
	// 특정 리뷰 조회
	public Review selectReviewById(Integer id) {
		return reviewMapper.selectReviewById(id);
	}
	
	// 리뷰 삭제
	public void deleteReview(Integer id) {
		reviewMapper.deleteReview(id);
	}
	
	// 리뷰 수정
	public void updateReview(Review review) {
		reviewMapper.updateReview(review);
	}

}