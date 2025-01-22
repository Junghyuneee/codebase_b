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
	
	// 특정 리뷰 조회
	//@Select("SELECT * FROM reviews WHERE id = #{id}")
    Review selectReviewById(int id);
	
    // 리뷰 조회수
    // @Update("UPDATE review SET views = views + 1 WHERE id = #{id}")
    void increaseViews(@Param("id") int id);
    
	// 리뷰 수정
	//@Update("UPDATE reviews SET title = #{title}, content = #{content} WHERE id = #{id}")
	//void updateReview(@Param("id") int id, @Param("title") String title, @Param("content") String content);
    void updateReview(Review review);
	
	// 리뷰 삭제
	//@Delete("DELETE FROM reviews WHERE id = #{id}")
	void deleteReview(int id);
	
	// 리뷰 좋아요 싫어요 증가
	void increaseLikes(int id);
	void increaseDislikes(int id);

	// 리뷰 좋아 싫어요 감소
	void decreaseLikes(int id);
	void decreaseDislikes(int id);

    List<Review> findByAuthor(String name);
}