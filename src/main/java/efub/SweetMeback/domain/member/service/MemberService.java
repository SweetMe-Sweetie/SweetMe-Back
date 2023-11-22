package efub.SweetMeback.domain.member.service;

import efub.SweetMeback.domain.heart.repository.HeartRepository;
import efub.SweetMeback.domain.heart.service.HeartService;
import efub.SweetMeback.domain.member.dto.MemberResponseDto;
import efub.SweetMeback.domain.member.dto.MemberUpdateRequestDto;
import efub.SweetMeback.domain.member.entity.Member;
import efub.SweetMeback.domain.member.repository.MemberRepository;
import efub.SweetMeback.domain.oauth.service.OAuthService;
import efub.SweetMeback.domain.payment.Service.PaymentService;
import efub.SweetMeback.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {
    private final OAuthService oAuthService;
    private final MemberRepository memberRepository;
    private final HeartRepository heartRepository;
    private final PostRepository postRepository;
    private final HeartService heartService;
    private final PaymentService paymentService;

    @Transactional(readOnly = true)
    public MemberResponseDto getMember(){
        Member member = oAuthService.getCurrentMember();
        String nickname = member.getNickname();
        String email = member.getEmail();
        String profileImage = member.getProfileImage();
        return new MemberResponseDto(nickname, email, profileImage);
    }

    public void updateMemberNickname(MemberUpdateRequestDto requestDto){
        Member member = oAuthService.getCurrentMember();
        member.updateMemberNickname(requestDto.getNickname());
        memberRepository.save(member);
    }

    public void deleteMember(Member member){
        heartRepository.deleteAllByMember(member);
        heartService.deleteForPost(member);
        paymentService.deleteForPost(member);
        postRepository.deleteAllByMember(member);
        memberRepository.delete(member);
    }
}
