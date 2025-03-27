package www.supercoding.com.web.dto.Post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import www.supercoding.com.web.dto.Comment.CommentDto;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostDto {
    private long id;
    private String title;
    private String content;
    private String email;
    private List<CommentDto> comments;
    private int likeCount;
}
