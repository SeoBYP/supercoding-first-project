package www.supercoding.com.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import www.supercoding.com.config.security.JwtTokenProvider;
import www.supercoding.com.repository.comment.CommentEntity;
import www.supercoding.com.repository.comment.CommentJpaRepogitory;
import www.supercoding.com.repository.like.LikeJpaRepository;
import www.supercoding.com.repository.post.PostEntity;
import www.supercoding.com.repository.post.PostJpaRepository;
import www.supercoding.com.repository.user.UserEntity;
import www.supercoding.com.repository.user.UserJpaRepository;
import www.supercoding.com.web.dto.Comment.CommentDto;
import www.supercoding.com.web.dto.Post.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostJpaRepository postJpaRepository;
    private final CommentJpaRepogitory commentJpaRepogitory;
    private final LikeJpaRepository likeJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public List<PostDto> getAllPosts(){
        return postJpaRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public PostDto getPost(long postId)
    {
        return toDto(postJpaRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다.")));
    }

    public List<PostDto> getPostsByAuthorEmail(String email)
    {
        Long authorId = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email + "의 유저가 작성한 게시물은 없습니다."))
                .getUserId();
        return postJpaRepository.findByAuthorId(authorId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public PostDto createPost(CreatePostRequestDto request)
    {
        String email = jwtTokenProvider.getEmailFromToken(request.getToken());
        Long userId = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 유저입니다."))
                .getUserId();

        PostEntity post = PostEntity.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .userId(userId)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();
        return toDto(postJpaRepository.save(post));
    }

    public PostDto updatePost(UpdatePostRequestDto request)
    {
        PostEntity postEntity = postJpaRepository.findById(request.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));

        String email = jwtTokenProvider.getEmailFromToken(request.getToken());
        Long userId = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 유저입니다."))
                .getUserId();

        if (postEntity.getUserId() != userId) {
            throw new SecurityException("수정 권한이 없습니다.");
        }

//        postEntity.update(request.getTitle(), request.getContent());

        return toDto(postEntity);
    }

    public void deletePost(DeletePostRequestDto request)
    {
        PostEntity postEntity = postJpaRepository.findById(request.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글이 없습니다."));

        String email = jwtTokenProvider.getEmailFromToken(request.getToken());
        Long userId = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 유저입니다."))
                .getUserId();
        if (postEntity.getUserId() != userId) {
            throw new SecurityException("수정 권한이 없습니다.");
        }
        postJpaRepository.delete(postEntity);
    }


    private PostDto toDto(PostEntity post)
    {
        Optional<CommentEntity> comments = commentJpaRepogitory.findByPostId(post.getPostId());
        Map<Long, String> userEmailMap = userJpaRepository.findAllById(
                comments.stream().map(CommentEntity::getUserId).collect(Collectors.toSet())
        ).stream().collect(Collectors.toMap(UserEntity::getUserId, UserEntity::getEmail));

        List<CommentDto> commentDtos = comments.stream()
                .map(c -> new CommentDto(c.getCommentId(),c.getContent(), userEmailMap.get(c.getUserId())))
                .toList();

        int likeCount = likeJpaRepository.countByPostId(post.getPostId());
        String email = userJpaRepository.findById(post.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 유저입니다."))
                .getEmail();
        return new PostDto(
                post.getPostId(),
                post.getTitle(),
                post.getContent(),
                email,
                commentDtos,
                likeCount
        );
    }
}
