package www.supercoding.com.web.dto.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateCommentRequestDto {
    private String content;
    private long postId;
    private String token;

}
