package www.supercoding.com.web.dto.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeletePostRequestDto {
    private long postId;
    private String token;
}
