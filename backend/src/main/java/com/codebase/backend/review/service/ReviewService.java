package com.codebase.backend.review.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		LocalDateTime currentTime = LocalDateTime.now(ZoneOffset.UTC);
		review.setCreatedDate(currentTime);
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
	@Transactional
	public void updateReview(int id, Review updatedReview) {
		/*
		 * try { reviewMapper.updateReview(id, title, content); // DB에서 업데이트 수행 return
		 * reviewMapper.selectReviewById(id); // 수정된 리뷰를 DB에서 다시 조회 } catch (Exception
		 * e) { e.printStackTrace(); // 오류 로그 추가 throw new
		 * RuntimeException("리뷰 수정 중 오류 발생", e); }
		 */
		updatedReview.setId(id);
		reviewMapper.updateReview(updatedReview);
	}
	
	// 리뷰 좋아요 증가
	public void increaseLikes(int id) {
		reviewMapper.increaseLikes(id);
	}
	
	// 리뷰 싫어요 증가
	public void increaseDislikes(int id) {
		reviewMapper.increaseDislikes(id);
	}
	
	// 리뷰 좋아요 감소
    public void decreaseLikes(int id) {
        reviewMapper.decreaseLikes(id);
    }
    
    // 싫어요 감소
    public void decreaseDislikes(int id) {
        reviewMapper.decreaseDislikes(id);
    }
}