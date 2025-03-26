package www.supercoding.com.web.controller;

import org.springframework.web.bind.annotation.*;
import www.supercoding.com.web.dto.Comment.Comment;
import www.supercoding.com.web.dto.Comment.CreateCommentRequest;
import www.supercoding.com.web.dto.Comment.DeleteCommentRequest;
import www.supercoding.com.web.dto.Comment.UpdateCommentRequest;
import www.supercoding.com.web.dto.Like.CreateLikeRequest;
import www.supercoding.com.web.dto.Like.DeleteLikeRequest;
import www.supercoding.com.web.dto.Like.Like;
import www.supercoding.com.web.dto.Post.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private List<Post> posts = new ArrayList<Post>();
    private List<Comment> comments = new ArrayList<Comment>();
    private List<Like> likes = new ArrayList<Like>();

    @GetMapping("/posts")
    private List<Post> getAllPosts() {
        return posts;
    }

    @GetMapping("/post-query")
    private List<Post> getPosts(@RequestBody GetPostRequest request) {
        return posts.stream()
                .filter(post -> post.getEmail().equals(request.getEmail()))
                .toList();
    }

    @PostMapping("/create-post")
    private CreatePostResponse createPost(@RequestBody CreatePostRequest request) {
        int id = posts.size() + 1;
        Post newPost = new Post(id, request.getTitle(), request.getContent(), request.getEmail());
        posts.add(newPost);
        System.out.println("새로운 글이 추가되었습니다. : " + newPost.getTitle());
        return new CreatePostResponse(newPost.getId(), newPost.getTitle(), newPost.getContent(),
                request.getEmail(), newPost.getCreated_at(), newPost.getUpdated_at());
    }

    @PutMapping("/update-post")
    public Post updatePost(@RequestBody UpdatePostRequest request) {
        Post foundedPost = posts.stream().filter(post -> post.getId() == request.getPostId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        posts.remove(foundedPost);
        Post newPost = new Post(foundedPost.getId(), request.getTitle(), request.getContent(), foundedPost.getEmail(), foundedPost.getCreated_at());
        posts.add(newPost);
        return newPost;
    }

    @DeleteMapping("/delete-post")
    public DeletePostResponse deletePost(@RequestBody DeletePostRequest request) {
        Post foundedPost = posts.stream().filter(post -> post.getId() == request.getPostId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        posts.remove(foundedPost);
        return new DeletePostResponse("삭제에 성공했습니다.");
    }

    // Comment

    @PostMapping("/post-create-comment")
    public List<Comment> createComment(@RequestBody CreateCommentRequest request) {
        Comment newComment = new Comment(request.getContent(), request.getPostId(), request.getEmail());
        comments.add(newComment);
        return comments.stream()
                .filter(comment -> comment.getPostId() == request.getPostId())
                .toList();
    }

    @PutMapping("/post-update-comment")
    public Comment updateComment(@RequestBody UpdateCommentRequest request) {
        Comment foundComment = comments.stream()
                .filter(comment -> comment.getId() == request.getCommentId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
        comments.remove(foundComment);
        Comment newComment = new Comment(foundComment.getId(), request.getContent(), request.getPostId(), request.getEmail());
        comments.add(newComment);
        return newComment;
    }

    @DeleteMapping("/post-delete-comment")
    public String deleteComment(@RequestBody DeleteCommentRequest request) {
        Comment foundComment = comments.stream()
                .filter(comment -> comment.getId() == request.getCommentId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다."));
        comments.remove(foundComment);
        return "삭제가 완료되었습니다.";
    }

    // 좋아요
    @PostMapping("/post-create-like")
    public List<Like> createLike(@RequestBody CreateLikeRequest request) {
        if (likes.stream().noneMatch(like -> like.getPostId() == request.getPostId() && like.getUserId() == request.getUserId()))
        {
            Like newLike = new Like(request.getPostId(), request.getUserId());
            likes.add(newLike);
        }

        return likes.stream().filter(like -> request.getPostId() == like.getPostId())
                .toList();
    }

    @DeleteMapping("/post-delete-like")
    public List<Like> deleteLike(@RequestBody DeleteLikeRequest request)
    {
        Like foundLike = likes.stream()
                .filter(like -> like.getPostId() == request.getPostId() && like.getUserId() == request.getUserId())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("좋아요를 누르지 않았습니다."));

        likes.remove(foundLike);
        return likes.stream().filter(like -> request.getPostId() == like.getPostId())
                .toList();
    }

}
