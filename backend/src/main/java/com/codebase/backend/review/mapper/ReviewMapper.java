package com.codebase.backend.review.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import com.codebase.backend.review.dto.Review;

@Mapper
public interface ReviewMapper {

	// 리뷰 저장
	//@Insert("INSERT INTO reviews (title, content) VALUES (#{title}, #{content})")
    void insertReview(Review review);
	
	// 모든 리뷰 조회
	//@Select("SELECT * FROM reviews")
    List<Review> selectAllReviews();
	
	// ID로 리뷰 조회
	//@Select("SELECT * FROM reviews WHERE id = #{id}")
    Review selectReviewById(int id);
	
    // 리뷰 조회수
    void increaseViews(@Param("id") int id);
    
    // 리뷰게시글 검색
    //List<Review> selectReviewBySearch(String search);
    
	// 리뷰 업데이트
	//@Update("UPDATE reviews SET title = #{title}, content = #{content} WHERE id = #{id}")
	void updateReview(Review review);
	
	// 리뷰 삭제
	//@Delete("DELETE FROM reviews WHERE id = #{id}")
	void deleteReview(int id);
}