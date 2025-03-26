package www.supercoding.com.config.security;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
//    @Value("${jwt.secret-key-source}")
    private String secretKeySource = "";
    private String secretKey;

    @PostConstruct
    public void setUp(){
        secretKey = Base64.getEncoder()
                .encodeToString(secretKeySource.getBytes());
    }


}
