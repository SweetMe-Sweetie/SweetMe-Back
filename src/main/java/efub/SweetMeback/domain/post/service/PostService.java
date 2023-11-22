package efub.SweetMeback.domain.post.service;

import efub.SweetMeback.domain.heart.repository.HeartRepository;
import efub.SweetMeback.domain.heart.service.HeartService;
import efub.SweetMeback.domain.member.entity.Member;
import efub.SweetMeback.domain.oauth.service.OAuthService;
import efub.SweetMeback.domain.payment.repository.PaymentRepository;
import efub.SweetMeback.domain.post.dto.PostFilteringRequest;
import efub.SweetMeback.domain.post.dto.PostRequestDto;
import efub.SweetMeback.domain.post.dto.PostResponseDtoWithHeart;
import efub.SweetMeback.domain.post.entity.Post;
import efub.SweetMeback.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final HeartRepository heartRepository;
    private final PaymentRepository paymentRepository;
    private final OAuthService oAuthService;
    private final HeartService heartService;

    public Post createPost(PostRequestDto requestDto){
        Member member = oAuthService.getCurrentMember();
        return postRepository.save(
                Post.builder()
                        .member(member)
                        .title(requestDto.getTitle())
                        .content(requestDto.getContent())
                        .deadline(requestDto.getDeadLine())
                        .startDate(requestDto.getStartDate())
                        .endDate(requestDto.getEndDate())
                        .people(requestDto.getPeople())
                        .view(0)
                        .recruitment(false)
                        .category(requestDto.getCategory())
                        .meeting(requestDto.getMeeting())
                        .contact(requestDto.getContact())
                        .promotion(false)
                        .isHeart(false)
                        .heartCount(0L)
                        .build()
        );
    }

    @Transactional(readOnly = true)
    public Post findPostById(Long postId){
        return postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글 입니다."));
    }


    public Integer updateView(Long postId) {
        return postRepository.updateView(postId);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDtoWithHeart> findAllPostsWithHeart() {
        List<Post> postList = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));

        List<PostResponseDtoWithHeart> responseList = new ArrayList<>();
        for (Post post : postList) {
            boolean isHeart = heartService.isHeartByMember(post);
            responseList.add(new PostResponseDtoWithHeart(post, isHeart));
        }

        return responseList;
    }

    @Transactional(readOnly = true)
    public List<PostResponseDtoWithHeart> findPostsByMember() {
        Member member = oAuthService.getCurrentMember();
        List<Post> postList = postRepository.findAllByMemberId(member.getId(), Sort.by(Sort.Direction.DESC, "createdDate"));

        List<PostResponseDtoWithHeart> responseList = new ArrayList<>();
        for (Post post : postList) {
            boolean isHeart = heartService.isHeartByMember(post);
            responseList.add(new PostResponseDtoWithHeart(post, isHeart));
        }

        return responseList;
    }

    @Transactional(readOnly = true)
    public List<PostResponseDtoWithHeart> findPostsByHeart() {
        Member member = oAuthService.getCurrentMember();
        List<Post> postList = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));

        List<PostResponseDtoWithHeart> responseList = new ArrayList<>();
        for (Post post : postList) {
            boolean isHeart = heartService.isHeartByMember(post);
            if (isHeart == true) {
                responseList.add(new PostResponseDtoWithHeart(post, true));
            }
        }

        return responseList;
    }

    @Transactional(readOnly = true)
    public List<PostResponseDtoWithHeart> findPostsByPromotion() {
        List<Post> postList = postRepository.findAllByPromotion(true);

        List<PostResponseDtoWithHeart> responseList = new ArrayList<>();
        for (Post post : postList) {
            boolean isHeart = heartService.isHeartByMember(post);
            responseList.add(new PostResponseDtoWithHeart(post, isHeart));
        }

        return responseList;
    }

    @Transactional(readOnly = true)
    public List<PostResponseDtoWithHeart> filtering(PostFilteringRequest filteringRequest) {
        List<Post> postList = postRepository.filteringAll(filteringRequest);

        List<PostResponseDtoWithHeart> responseList = new ArrayList<>();
        for (Post post : postList) {
            boolean isHeart = heartService.isHeartByMember(post);
            responseList.add(new PostResponseDtoWithHeart(post, isHeart));
        }

        return responseList;
    }

    public void removePost(Long postId){
        Member member = oAuthService.getCurrentMember();
        Post post = postRepository.findByIdAndMemberId(postId, member.getId())
                .orElseThrow(()->new IllegalArgumentException("잘못된 접근입니다."));
        heartRepository.deleteByPost(post);
        paymentRepository.deleteByPost(post);
        postRepository.delete(post);
    }

    public Post modifyPost(Long postId, PostRequestDto requestDto){
        Member member = oAuthService.getCurrentMember();
        Post post = postRepository.findByIdAndMemberId(postId, member.getId())
                .orElseThrow(()->new IllegalArgumentException("잘못된 접근입니다."));
        post.modifyPost(requestDto);
        return post;
    }

    public Post changeRecruitment(Long postId){
        Member member = oAuthService.getCurrentMember();
        Post post = postRepository.findByIdAndMemberId(postId, member.getId())
                .orElseThrow(()->new IllegalArgumentException("잘못된 접근입니다."));
        post.setRecruitment(!post.isRecruitment());
        postRepository.save(post);
        return post;

    }
}
