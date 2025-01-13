package com.codebase.backend.comment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codebase.backend.comment.dto.CommentDto;
import com.codebase.backend.comment.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 댓글 작성
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment) {
        CommentDto createdComment = commentService.createComment(comment);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    // 특정 게시글의 댓글 목록 조회
    @GetMapping("/post/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable("postId") long postId) {
        List<CommentDto> comments = commentService.getCommentsByPostId(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    // 댓글 수정
    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("id") long id, @RequestBody CommentDto comment) {
        comment.setId(id);
        CommentDto updatedComment = commentService.updateComment(id, comment);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("id") Long id, @RequestBody CommentDto commentDto) {
        commentDto.setId(id); // ID를 설정하여 수정할 댓글을 명시
        commentService.updateComment(commentDto);
        return ResponseEntity.ok(commentDto);

    public ResponseEntity<Void> deleteComment(@PathVariable("id") long id) {
        commentService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}