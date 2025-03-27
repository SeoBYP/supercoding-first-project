package www.supercoding.com.web.dto.Like;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateLikeRequestDto {
    private long postId;
    private String token;
}
