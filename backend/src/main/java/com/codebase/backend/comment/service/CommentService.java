package com.codebase.backend.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codebase.backend.comment.dto.CommentDTO;
import com.codebase.backend.comment.mapper.CommentMapper;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CommentService {
 private final CommentMapper commentMapper;

 public List<CommentDTO> getCommentsByPostId(Long postId) {
     return commentMapper.getCommentsByPostId(postId);
 }

 public CommentDTO addComment(CommentDTO comment) {
     commentMapper.insertComment(comment);
     return comment;
 }

 public CommentDTO updateComment(CommentDTO comment) {
     commentMapper.updateComment(comment);
     return comment;
 }

 public void deleteComment(Long id) {
     commentMapper.deleteComment(id);
 }
}