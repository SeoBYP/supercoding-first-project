package www.supercoding.com.repository.like;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @Column(name = "likeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long likeId;

    @Column(name = "postId")
    private long postId;

    @Column(name = "userId")
    private long userId;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return likeId == like.likeId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(likeId);
    }
}
