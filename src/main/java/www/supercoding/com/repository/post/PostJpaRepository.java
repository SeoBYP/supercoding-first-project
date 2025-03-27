package www.supercoding.com.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostJpaRepository extends JpaRepository<PostEntity,Long> {
    Optional<PostEntity> findByAuthorId(Long authorId);
}
