package www.supercoding.com.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import www.supercoding.com.config.security.JwtTokenProvider;
import www.supercoding.com.repository.like.LikeEntity;
import www.supercoding.com.repository.like.LikeJpaRepository;
import www.supercoding.com.repository.user.UserJpaRepository;
import www.supercoding.com.web.dto.Like.CreateLikeRequestDto;
import www.supercoding.com.web.dto.Like.DeleteLikeRequestDto;

@Service
@AllArgsConstructor
public class LikeService {
    private final LikeJpaRepository likeJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public void like(CreateLikeRequestDto request)
    {
        String email = jwtTokenProvider.getEmailFromToken(request.getToken());
        Long userId = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 유저입니다."))
                .getUserId();

        if (likeJpaRepository.existsByUserIdAndPostId(userId, request.getPostId())) {
            throw new IllegalStateException("이미 좋아요를 눌렀습니다.");
        }
        likeJpaRepository.save(LikeEntity.builder()
                .postId(request.getPostId())
                .userId(userId)
                .build());
    }

    public void unlike(DeleteLikeRequestDto request)
    {
        String email = jwtTokenProvider.getEmailFromToken(request.getToken());
        Long userId = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 유저입니다."))
                .getUserId();

        if (likeJpaRepository.existsByUserIdAndPostId(userId, request.getPostId())) {
            throw new IllegalStateException("이미 좋아요를 눌렀습니다.");
        }
        likeJpaRepository.deleteByUserIdAndPostId(userId, request.getPostId());
    }

}
