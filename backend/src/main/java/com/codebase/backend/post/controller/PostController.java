package com.codebase.backend.post.controller;

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

import com.codebase.backend.post.dto.PostDTO;
import com.codebase.backend.post.service.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 등록
    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostDTO post) {
        postService.createPost(post);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 게시글 목록 조회
    @GetMapping
    public ResponseEntity<List<PostDTO>> selectAllPosts() {
        List<PostDTO> posts = postService.selectAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    // 특정 게시글 조회
    @GetMapping("/detail/{id}")
    public ResponseEntity<PostDTO> selectPostById(@PathVariable("id") Long id) {
        PostDTO post = postService.selectPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    // 게시글 조회수 증가
    @PutMapping("/increaseViews/{id}")
    public ResponseEntity<String> increaseViews(@PathVariable("id") Long id) {
        try {
            postService.increaseViews(id);
            return ResponseEntity.ok("조회수 증가 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("조회수 증가 실패: " + e.getMessage());
        }
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id) {
        try {
            postService.deletePost(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }

    // 게시글 수정
    @PutMapping("/update/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable("id") Long id, @RequestBody PostDTO post) {
        try {
            PostDTO updatedPost = postService.updatePost(id, post.getTopic(), post.getTitle(), post.getContent());
            return ResponseEntity.ok(updatedPost); // 수정된 게시글 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
    }
}
