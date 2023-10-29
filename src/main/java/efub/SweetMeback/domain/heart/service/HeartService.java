package efub.SweetMeback.domain.heart.service;

import efub.SweetMeback.domain.heart.entity.Heart;
import efub.SweetMeback.domain.heart.repository.HeartRepository;
import efub.SweetMeback.domain.member.entity.Member;
import efub.SweetMeback.domain.member.service.MemberService;
import efub.SweetMeback.domain.oauth.service.OAuthService;
import efub.SweetMeback.domain.post.entity.Post;
import efub.SweetMeback.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;
    private final PostService postService;
    private final OAuthService oAuthService;

    //좋아요 생성
    public void create(Long postId){
        Member member = oAuthService.getCurrentMember();
        Post post = postService.findPostById(postId);

        if(isExistsByMemberAndPost(member, post)){
            throw new RuntimeException("이미 좋아요 누른 게시물 입니다.");
        }

        Heart heart = Heart.builder()
                .post(post)
                .member(member)
                .build();

        heartRepository.save(heart);
    }

    public void delete(Long postId){
        Post post = postService.findPostById(postId);
        Member member = oAuthService.getCurrentMember();

        Heart heart = heartRepository.findByMemberAndPost(member, post)
                .orElseThrow(()->new RuntimeException("좋아요가 존재하지 않습니다."));

        heartRepository.delete(heart);
    }

    @Transactional(readOnly = true)
    public boolean isExistsByMemberAndPost(Member member, Post post) {
        return heartRepository.existsByMemberAndPost(member, post);
    }
}
