package com.codebase.backend.review.controller;

import java.util.List;

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

@RestController //JSON 형식으로 자동 변환하여 반환
@RequestMapping("/api/review")
//@CrossOrigin(origins = "http://localhost:3003")
public class ReviewController {

    //@Autowired
    private final ReviewService reviewService;
    
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
    @GetMapping("/detail/{id}")
    public ResponseEntity<Review> selectReviewById(@PathVariable("id") int id){
    	Review review = reviewService.selectReviewById(id);
    	return new ResponseEntity<>(review, HttpStatus.OK);
    }
    
	 // 리뷰 조회수
    @PutMapping("/increaseViews/{id}")
    public ResponseEntity<String> increaseViews(@PathVariable("id") int id){
    	System.out.println("increaseViews 호출됨, id: " + id); //디버깅 
    	try {
            reviewService.increaseViews(id);
            return ResponseEntity.ok("조회수 증가 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("조회수 증가 실패: " + e.getMessage());
        }
    }
    
    // 리뷰 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable("id") int id){
    	try {
            // id를 int로 변환
            //int reviewId = Integer.parseInt(id);
            reviewService.deleteReview(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }
    
    // 리뷰 수정
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateReview(@PathVariable("id") int id, @RequestBody Review updatedReview){
    	try {
            reviewService.updateReview(id, updatedReview);
            return ResponseEntity.ok("리뷰가 성공적으로 수정되었습니다."); // 수정된 리뷰 반환
        } catch (Exception e) {
            e.printStackTrace(); // 예외 로그 추가
            return ResponseEntity.status(500).body("리뷰 수정에 실패했습니다."); // 500 오류 반환
        }
    }
    
    // 리뷰 좋아요 증가 
    @PostMapping("/like/{id}")
    public void likeReview(@PathVariable("id") int id) {
    	reviewService.increaseLikes(id);
    }
    
    // 리뷰 싫어요 증가
    @PostMapping("/dislike/{id}")
    public void dislikeReview(@PathVariable("id") int id) {
    	reviewService.increaseDislikes(id);
    }
    
    // 리뷰 좋아요 증가 
    @PostMapping("/unlike/{id}")
    public void unlikeReview(@PathVariable("id") int id) {
    	reviewService.decreaseLikes(id);
    }
    
    // 리뷰 싫어요 증가
    @PostMapping("/undislike/{id}")
    public void undislikeReview(@PathVariable("id") int id) {
    	reviewService.decreaseDislikes(id);
    }
}