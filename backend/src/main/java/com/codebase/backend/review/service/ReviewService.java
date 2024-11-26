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
	public Review selectReviewById(int id) {
		return reviewMapper.selectReviewById(id);
	}
	
	// 리뷰 조회수
	public void increaseViews(int id) {
		reviewMapper.increaseViews(id);
	}
		
	// 리뷰 삭제
	public void deleteReview(int id) {
		reviewMapper.deleteReview(id);
	}
	
	// 리뷰 수정
	public Review updateReview(int id, String title, String content) {
		reviewMapper.updateReview(id, title, content); //DB에서 업데이트 수행
		return reviewMapper.selectReviewById(id); //수정된 리뷰를 DB에서 다시 조회
	}

}