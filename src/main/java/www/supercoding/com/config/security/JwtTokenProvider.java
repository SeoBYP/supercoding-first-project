package www.supercoding.com.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component // Spring Bean 으로 등록
@RequiredArgsConstructor // final 필드를 생성자 주입
public class JwtTokenProvider {
    // JWT 서명을 위한 비밀 키 (base64로 인코딩된 문자열로 사용)
    private final String secretKey = Base64.getEncoder().encodeToString("super-coding".getBytes());
    private final long tokenValidMillisecond = 1000 * 60 * 60; // 1 hour

    // HTTP 요청 헤더에서 JWT 토큰 추출 ("X-AUTH-TOKEN" 헤더에서 꺼냄)
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    // 토큰 생성
    public String createToken(String email)
    {
        Claims claims = Jwts.claims()
                .setSubject(email); // 토큰의 subject = email

        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 설정한 클레임들 포함
                .setIssuedAt(now) // 생성 시간
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond)) // 만료 시간
                .signWith(SignatureAlgorithm.HS256, secretKey) // HMAC-SHA256 알고리즘으로 서명
                .compact(); // 최종적으로 문자열 토큰 생성
    }

    // 토큰에서 이메일(subject) 추출
    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();// subject = email
    }

    public boolean validateToken(String jwtToken)
    {
        try
        {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey) // 토큰 생성 시 사용했던 키로 서명 검증
                    .parseClaimsJws(jwtToken)
                    .getBody();
            Date now = new Date();
            return claims.getExpiration().after(now); // 만료되지 않았다면 true
        } catch (Exception e) {
            return false; // 예외 발생 시 토큰 무효
        }
    }
}
