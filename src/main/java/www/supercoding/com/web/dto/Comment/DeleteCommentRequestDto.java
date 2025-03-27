package www.supercoding.com.web.dto.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DeleteCommentRequestDto {
    private long commentId;
    private long postId;
    private String token;
}
