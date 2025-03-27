package www.supercoding.com.web.dto.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import www.supercoding.com.repository.comment.CommentEntity;

@Getter
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String content;
    private String email;

    public static CommentDto from(CommentEntity comment,String email) {
        return new CommentDto(
                comment.getCommentId(),
                comment.getContent(),
                email
        );
    }
}
