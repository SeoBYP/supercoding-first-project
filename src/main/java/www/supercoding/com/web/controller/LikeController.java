package www.supercoding.com.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import www.supercoding.com.service.LikeService;
import www.supercoding.com.service.PostService;
import www.supercoding.com.web.dto.Like.CreateLikeRequestDto;
import www.supercoding.com.web.dto.Like.DeleteLikeRequestDto;
import www.supercoding.com.web.dto.Post.PostDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class LikeController {
    private final LikeService likeService;
    private final PostService postService;

    @PostMapping
    public PostDto like(@RequestBody CreateLikeRequestDto request) {
        likeService.like(request);
        return postService.getPost(request.getPostId());
    }

    @DeleteMapping
    public PostDto unlike(@RequestBody DeleteLikeRequestDto request) {
        likeService.unlike(request);
        return postService.getPost(request.getPostId());
    }
}
