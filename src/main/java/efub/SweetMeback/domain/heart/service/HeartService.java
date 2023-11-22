package efub.SweetMeback.domain.heart.service;

import efub.SweetMeback.domain.heart.entity.Heart;
import efub.SweetMeback.domain.heart.repository.HeartRepository;
import efub.SweetMeback.domain.member.entity.Member;
import efub.SweetMeback.domain.oauth.service.OAuthService;
import efub.SweetMeback.domain.post.entity.Post;
import efub.SweetMeback.domain.post.repository.PostRepository;
import efub.SweetMeback.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final OAuthService oAuthService;
    private final PostRepository postRepository;

    @Autowired
    @Lazy
    private PostService postService;


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
        increaseHeartCount(post);
    }

    public void delete(Long postId){
        Post post = postService.findPostById(postId);
        Member member = oAuthService.getCurrentMember();

        Heart heart = heartRepository.findByMemberAndPost(member, post)
                .orElseThrow(()->new RuntimeException("좋아요가 존재하지 않습니다."));

        heartRepository.delete(heart);
        decreaseHeartCount(post);
    }

    //탈퇴하려는 사용자가 작성한 게시물의 좋아요 모두 삭제
    public void deleteForPost(Member member){
        List<Post> postList = postRepository.findAllByMember(member);
        for(Post post : postList){
            heartRepository.deleteByPost(post);
        }
    }

    @Transactional(readOnly = true)
    public boolean isExistsByMemberAndPost(Member member, Post post) {
        return heartRepository.existsByMemberAndPost(member, post);
    }

    private void increaseHeartCount(Post post) {
        post.setHeartCount(post.getHeartCount() + 1);
    }

    private void decreaseHeartCount(Post post) {
        post.setHeartCount(post.getHeartCount() - 1);
    }

    @Transactional(readOnly = true)
    public boolean isHeartByMember(Post post) {
        if (oAuthService.getCurrentMember() == null) {
            return false;
        }

        Member member = oAuthService.getCurrentMember();

        return heartRepository.existsByMemberAndPost(member, post);
    }

    @Transactional(readOnly = true)
    public Long getHeartCount(Post post) {
        return post.getHeartCount();
    }
}
