package efub.SweetMeback.domain.post.service;

import efub.SweetMeback.domain.heart.service.HeartService;
import efub.SweetMeback.domain.member.entity.Member;
import efub.SweetMeback.domain.oauth.service.OAuthService;
import efub.SweetMeback.domain.post.dto.PostRequestDto;
import efub.SweetMeback.domain.post.dto.PostResponseDto;
import efub.SweetMeback.domain.post.dto.PostResponseDtoWithHeart;
import efub.SweetMeback.domain.post.entity.Post;
import efub.SweetMeback.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
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
    public List<PostResponseDto> findAllPosts() {
        List<Post> postList = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
        return postList.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponseDtoWithHeart> findAllPostsWithHeart() {
        List<Post> postList = postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));

        List<PostResponseDtoWithHeart> responseList = new ArrayList<>();
        for (Post post : postList) {
            boolean isHeart = heartService.isHeartByMember(post);
            Long heartCount = heartService.getHeartCount(post);
            responseList.add(new PostResponseDtoWithHeart(post, isHeart, heartCount));
        }

        return responseList;
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findPostsByMember() {
        Member member = oAuthService.getCurrentMember();
        List<Post> postList = postRepository.findAllByMemberId(member.getId());
        return postList.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }

//    @Transactional(readOnly = true)
//    public List<PostResponseDto> findPostsByHeart() {
//        Member member = oAuthService.getCurrentMember();
//
//
//    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findPostsByPromotion() {
        List<Post> postList = postRepository.findAllByPromotion(true);
        return postList.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }
}
