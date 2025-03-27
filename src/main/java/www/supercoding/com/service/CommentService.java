package www.supercoding.com.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import www.supercoding.com.config.security.JwtTokenProvider;
import www.supercoding.com.repository.comment.CommentEntity;
import www.supercoding.com.repository.comment.CommentJpaRepogitory;
import www.supercoding.com.repository.post.PostEntity;
import www.supercoding.com.repository.post.PostJpaRepository;
import www.supercoding.com.repository.user.UserJpaRepository;
import www.supercoding.com.web.dto.Comment.CreateCommentRequestDto;
import www.supercoding.com.web.dto.Comment.DeleteCommentRequestDto;
import www.supercoding.com.web.dto.Comment.UpdateCommentRequestDto;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentJpaRepogitory commentJpaRepogitory;
    private final PostJpaRepository postJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public void createComment(CreateCommentRequestDto request)
    {
        PostEntity postEntity = postJpaRepository.findById(request.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("게시글 없음"));

        String email = jwtTokenProvider.getEmailFromToken(request.getToken());
        Long userId = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 유저입니다."))
                .getUserId();
        CommentEntity commentEntity = CommentEntity.builder()
                .postEntity(postEntity)
                .userId(userId)
                .content(request.getContent())
                .build();
        commentJpaRepogitory.save(commentEntity);
    }

    public void updateComment(UpdateCommentRequestDto request)
    {
        CommentEntity comment = commentJpaRepogitory.findById(request.getCommentId())
                .orElseThrow(() -> new IllegalArgumentException("댓글 없음"));

        String email = jwtTokenProvider.getEmailFromToken(request.getToken());
        Long userId = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 유저입니다."))
                .getUserId();

        if (comment.getUserId() != userId) {
            throw new SecurityException("수정 권한 없음");
        }

//        comment.update(content);
    }

    public void deleteComment(DeleteCommentRequestDto request)
    {
        CommentEntity comment = commentJpaRepogitory.findById(request.getCommentId())
                .orElseThrow(() -> new IllegalArgumentException("댓글 없음"));

        String email = jwtTokenProvider.getEmailFromToken(request.getToken());
        Long userId = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 유저입니다."))
                .getUserId();

        if (comment.getUserId() != userId) {
            throw new SecurityException("수정 권한 없음");
        }

        commentJpaRepogitory.delete(comment);
    }

}
