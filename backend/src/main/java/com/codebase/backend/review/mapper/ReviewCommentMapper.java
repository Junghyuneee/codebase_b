package com.codebase.backend.review.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.codebase.backend.review.dto.ReviewComment;

@Mapper
public interface ReviewCommentMapper {

	void addComment(ReviewComment comment); // 댓글 추가
	List<ReviewComment> getCommentsByReviewId(int reviewId); // 특정 리뷰의 댓글 가져오기
}
