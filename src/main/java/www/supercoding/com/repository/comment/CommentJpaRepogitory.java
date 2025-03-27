package www.supercoding.com.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentJpaRepogitory extends JpaRepository<CommentEntity,Long> {
    Optional<CommentEntity> findByPostId(long postId);
}
