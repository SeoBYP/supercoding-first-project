package www.supercoding.com.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import www.supercoding.com.config.security.JwtTokenProvider;
import www.supercoding.com.repository.user.UserEntity;
import www.supercoding.com.repository.user.UserJpaRepository;
import www.supercoding.com.web.dto.Sign.SignUpRequestDto;
import www.supercoding.com.web.dto.Sign.SigninRequestDto;
import www.supercoding.com.web.dto.Sign.SigninResponseDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void signUp(SignUpRequestDto request)
    {
        if(userJpaRepository.findByEmail(request.getEmail()).isPresent())
        {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다");
        }
        if(!request.getPassword().equals(request.getConfirm_password()))
        {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        String encoded = passwordEncoder.encode(request.getPassword());
        userJpaRepository.save(UserEntity.builder()
                        .email(request.getEmail())
                        .password(encoded)
                        .build());
    }

    public SigninResponseDto signIn(SigninRequestDto request)
    {
        UserEntity user = userJpaRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일 혹은 비밀번호가 일치하지 않습니다."));

        if(passwordEncoder.matches(request.getPassword(), user.getPassword()))
        {
            throw  new IllegalArgumentException("이메일 혹은 비밀번호가 일치하지 않습니다.");
        }

        String token = jwtTokenProvider.createToken(user.getEmail());
        return new SigninResponseDto(user.getEmail(),token);
    }
}
