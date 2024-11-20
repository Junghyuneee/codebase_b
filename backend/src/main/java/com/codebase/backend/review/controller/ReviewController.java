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
    public ResponseEntity<Integer> createReview(@RequestBody Review review){
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
    public ResponseEntity<?> increaseViews(@PathVariable int id){
    	try {
            reviewService.increaseViews(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류");
        }
    }
    
    // 리뷰게시글 검색
    //@GetMapping
    //public List<Review> getReview(@RequestParam(value = "search", required = false, defaultValue = "") String search) {
    //	return reviewService.getReview(search);
    //}
    
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
    public ResponseEntity<Void> updateReview(@PathVariable int id, @RequestBody Review review){
    	review.setId(id); // ID를 경로에서 전달받은 값으로 설정
    	reviewService.updateReview(review);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
}