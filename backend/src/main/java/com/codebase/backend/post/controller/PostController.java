package com.codebase.backend.post.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if (post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
        return ResponseEntity.ok(post);
    }

    // 게시글 조회수 증가
    @PutMapping("/increaseViews/{id}")
    public ResponseEntity<String> increaseViews(@PathVariable("id") Long id) {
        postService.increaseViews(id);
        return ResponseEntity.ok("조회수 증가 성공");
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id) {
        if (postService.selectPostById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
        postService.deletePost(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    // 게시글 수정
    @PutMapping("/update/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable("id") Long id, @RequestBody PostDTO post) {
        PostDTO updatedPost = postService.updatePost(id, post.getTopic(), post.getTitle(), post.getContent());
        if (updatedPost == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
        return ResponseEntity.ok(updatedPost);
    }

    // 좋아요 처리
    @PutMapping("/{id}/like")
    public ResponseEntity<PostDTO> likePost(@PathVariable Long id) {
        PostDTO updatedPost = postService.likePost(id);
        return ResponseEntity.ok(updatedPost);
    }

    // 싫어요 처리
    @PutMapping("/{id}/dislike")
    public ResponseEntity<PostDTO> dislikePost(@PathVariable Long id) {
        PostDTO updatedPost = postService.dislikePost(id);
        return ResponseEntity.ok(updatedPost);
    }
}
