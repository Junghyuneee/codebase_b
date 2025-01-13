package com.codebase.backend.post.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<PostDTO> selectPostById(@PathVariable("id") int id) {
        PostDTO post = postService.selectPostById(id);
        return ResponseEntity.ok(post); // 404 Not Found는 서비스에서 처리
    }

    // 게시글 조회수 증가
    @PutMapping("/increaseViews/{id}")
    public ResponseEntity<String> increaseViews(@PathVariable("id") int id) {
        System.out.println("increaseViews 호출됨, id: " + id); // 디버깅 
        try {
            postService.increaseViews(id);
            return ResponseEntity.ok("조회수 증가 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("조회수 증가 실패: " + e.getMessage());
        }
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") int id) {
        boolean isDeleted = postService.deletePost(id);
        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found
        }
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    // 게시글 수정
    @PutMapping("/update/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable("id") int id, @RequestBody PostDTO post) {
        PostDTO updatedPost = postService.updatePost(id, post.getTopic(), post.getTitle(), post.getContent(), post.getTags());
        return ResponseEntity.ok(updatedPost); // 404 Not Found는 서비스에서 처리
    }

    // 게시글 좋아요
    @PostMapping("/like/{id}")
    public ResponseEntity<Void> likeReview(@PathVariable("id") int id, @RequestParam("userId") String userId) {
        postService.updateLikes(id, userId);
        return ResponseEntity.ok().build();
    }
    
    // 게시글 싫어요
    @PostMapping("/dislike/{id}")
    public ResponseEntity<Void> dislikeReview(@PathVariable("id") int id, @RequestParam("userId") String userId) {
        postService.updateDislikes(id, userId);
        return ResponseEntity.ok().build();
    }

    // 현재 사용자의 좋아요/싫어요 상태 조회
    @GetMapping("/{id}/like-status")
    public ResponseEntity<Map<String, Boolean>> getLikeStatus(@PathVariable("id") int id, @RequestParam("userId") String userId) {
        Map<String, Boolean> likeStatus = postService.getLikeStatus(id, userId);
        return ResponseEntity.ok(likeStatus);
    }
}