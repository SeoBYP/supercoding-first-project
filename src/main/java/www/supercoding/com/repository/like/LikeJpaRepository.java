package www.supercoding.com.repository.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface LikeJpaRepository extends JpaRepository<LikeEntity,Long> {

    Integer countByPostId(long postId);

    boolean existsByUserIdAndPostId(Long userId, long postId);

    void deleteByUserIdAndPostId(Long userId, long postId);
}
