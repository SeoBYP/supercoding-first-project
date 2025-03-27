package www.supercoding.com.repository.comment;

import jakarta.persistence.*;
import lombok.*;
import www.supercoding.com.repository.post.PostEntity;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "comments")
@Builder
public class CommentEntity {
    @Id
    @Column(name = "commentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private PostEntity postEntity;

    @Column(name = "userId")
    private long userId;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity comment = (CommentEntity) o;
        return commentId == comment.commentId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(commentId);
    }
}
