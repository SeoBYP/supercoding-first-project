package www.supercoding.com.web.controller;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import www.supercoding.com.service.UserService;
import www.supercoding.com.web.dto.Sign.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequestDto requestDto)
    {
        userService.signUp(requestDto);
        return ResponseEntity.ok("회원가입 성공");
    }

    @GetMapping("/singin")
    public ResponseEntity<SigninResponseDto> singin(@RequestBody SigninRequestDto requestDto)
    {
        return ResponseEntity.ok(userService.signIn(requestDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // 클라이언트 측에서 토큰 삭제 (서버에 저장된 세션이 없기 때문에 실제 로직은 필요 없음)
        return ResponseEntity.ok("로그아웃 완료");
    }
}
