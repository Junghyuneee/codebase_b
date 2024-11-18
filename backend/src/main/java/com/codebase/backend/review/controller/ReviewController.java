package com.codebase.backend.review.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codebase.backend.review.dto.Review;
import com.codebase.backend.review.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
//@CrossOrigin(origins = "http://localhost:3003")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;
    
    public ReviewController(ReviewService reviewService) {
    	this.reviewService = reviewService;
    }

    // 리뷰 등록
    @PostMapping
    public ResponseEntity<Void> createReview(@RequestBody Review review){
    	reviewService.createReview(review);
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 리뷰 목록 조회
    @GetMapping
    public ResponseEntity<List<Review>> selectAllReviews(){
    	List<Review> reviews = reviewService.selectAllReviews();
    	return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    
    // 특정 리뷰 조회
    @GetMapping("/selectReview/{id}")
    public ResponseEntity<Review> selectReviewById(@PathVariable Integer id){
    	Review review = reviewService.selectReviewById(id);
    	return new ResponseEntity<>(review, HttpStatus.OK);
    }
    
    // 리뷰 삭제
    @DeleteMapping("/deleteReview/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer id){
    	reviewService.deleteReview(id);
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // 리뷰 수정
    @PutMapping("/updateReview/{id}")
    public ResponseEntity<Void> updateReview(@PathVariable Integer id, @RequestBody Review review){
    	review.setId(id); // ID를 경로에서 전달받은 값으로 설정
    	reviewService.updateReview(review);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
}