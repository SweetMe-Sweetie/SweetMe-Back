package efub.SweetMeback.domain.post.repository;

import efub.SweetMeback.domain.post.entity.Post;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Modifying
    @Query("update Post p set p.view = p.view + 1 where p.id = :id")
    Integer updateView(@Param("id") Long id);

    List<Post> findAllByMemberId(Long memberId, Sort createdDate);

    List<Post> findAllByPromotion(boolean promotion);
}
