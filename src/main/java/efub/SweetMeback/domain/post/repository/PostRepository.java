package efub.SweetMeback.domain.post.repository;

import efub.SweetMeback.domain.member.entity.Member;
import efub.SweetMeback.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByIdAndMemberId(Long id, Long memberId);
}
