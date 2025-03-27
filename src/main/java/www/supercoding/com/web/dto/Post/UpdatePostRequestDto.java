package www.supercoding.com.web.dto.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePostRequestDto {
    private long postId;
    private String title;
    private String content;
    private String token;
}
