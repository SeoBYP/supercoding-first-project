package www.supercoding.com.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import www.supercoding.com.service.PostService;
import www.supercoding.com.web.dto.Post.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    private List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/post-query")
    private List<PostDto> getPosts(@RequestParam String email) {
        return postService.getPostsByAuthorEmail(email);
    }

    @PostMapping("/create-post")
    private PostDto createPost(@RequestBody CreatePostRequestDto request) {
        return postService.createPost(request);
    }

    @PutMapping("/update-post")
    public PostDto updatePost(@RequestBody UpdatePostRequestDto request) {
        return postService.updatePost(request);
    }

    @DeleteMapping("/delete-post")
    public ResponseEntity<String> deletePost(@RequestBody DeletePostRequestDto request) {
        postService.deletePost(request);
        return ResponseEntity.ok("삭제에 성공했습니다.");
    }

}
