package efub.SweetMeback.domain.heart.repository;

import efub.SweetMeback.domain.heart.entity.Heart;
import efub.SweetMeback.domain.member.entity.Member;
import efub.SweetMeback.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    // 작성자와 게시글이 있는지 확인
    boolean existsByMemberAndPost(Member member, Post post);

    //작성자와 게시글 기준으로 heart 조회
    Optional<Heart> findByMemberAndPost(Member member, Post post);

    //사용자가 누른 좋아요 모두 삭제
    void deleteAllByMember(Member member);
}
