package www.supercoding.com.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import www.supercoding.com.service.CommentService;
import www.supercoding.com.service.PostService;
import www.supercoding.com.web.dto.Comment.CreateCommentRequestDto;
import www.supercoding.com.web.dto.Comment.DeleteCommentRequestDto;
import www.supercoding.com.web.dto.Comment.UpdateCommentRequestDto;
import www.supercoding.com.web.dto.Post.PostDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;
    private final PostService postService;
    @PostMapping
    public PostDto create(@RequestBody CreateCommentRequestDto request) {
        commentService.createComment(request);
        return postService.getPost(request.getPostId());
    }

    @PutMapping("/{id}")
    public PostDto update(@RequestBody UpdateCommentRequestDto request) {
        commentService.updateComment(request);
        return postService.getPost(request.getPostId());
    }

    @DeleteMapping("/{id}")
    public PostDto delete(@RequestBody DeleteCommentRequestDto request) {
        commentService.deleteComment(request);
        return postService.getPost(request.getPostId());
    }
}
