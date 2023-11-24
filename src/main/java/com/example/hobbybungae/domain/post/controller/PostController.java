package com.example.hobbybungae.domain.post.controller;

import com.example.hobbybungae.exception.AuthorizeException;
import com.example.hobbybungae.exception.PostNotFoundException;
import com.example.hobbybungae.domain.post.dto.PostRequestDto;
import com.example.hobbybungae.domain.post.dto.PostResponseDto;
import com.example.hobbybungae.exception.ErrorResponseDto;
import com.example.hobbybungae.security.UserDetailsImpl;
import com.example.hobbybungae.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hobby-bungae/v1/hobbies")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> addPost(
            @RequestBody PostRequestDto requestDto
    ) {
        PostResponseDto responseDto = postService.addPost(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPost(
            @PathVariable Long postId
    ) {
        PostResponseDto responseDto = postService.getPost(postId);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getPosts() {
        List<PostResponseDto> responseDto = postService.getPosts();
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(
            @PathVariable Long postId,
            @RequestBody PostRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails
            ) {
        PostResponseDto responseDto = postService.updatePost(postId, requestDto);
        return ResponseEntity.ok(responseDto);
    }


    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long postId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

}