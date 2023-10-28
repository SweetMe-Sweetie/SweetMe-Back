package efub.SweetMeback.domain.post.service;

import efub.SweetMeback.domain.member.entity.Member;
import efub.SweetMeback.domain.member.repository.MemberRepository;
import efub.SweetMeback.domain.post.dto.PostRequestDto;
import efub.SweetMeback.domain.post.entity.Post;
import efub.SweetMeback.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public Post createPost(PostRequestDto requestDto){
        Member member = memberRepository.findById(requestDto.getMemberId())
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 계정입니다."));
        return postRepository.save(
                Post.builder()
                        .member(member)
                        .title(requestDto.getTitle())
                        .content(requestDto.getContent())
                        .deadline(requestDto.getDeadLine())
                        .startDate(requestDto.getStartDate())
                        .endDate(requestDto.getEndDate())
                        .people(requestDto.getPeople())
                        .view(0L)
                        .recruitment(false)
                        .category(requestDto.getCategory())
                        .meeting(requestDto.getMeeting())
                        .contact(requestDto.getContact())
                        .promotion(false)
                        .build()
        );
    }

    public Post findPostById(Long postId){
        return postRepository.findById(postId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시글 입니다."));
    }
}
