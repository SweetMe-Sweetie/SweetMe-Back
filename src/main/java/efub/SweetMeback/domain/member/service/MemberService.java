package efub.SweetMeback.domain.member.service;

import efub.SweetMeback.domain.member.dto.MemberResponseDto;
import efub.SweetMeback.domain.member.dto.MemberUpdateRequestDto;
import efub.SweetMeback.domain.member.entity.Member;
import efub.SweetMeback.domain.member.repository.MemberRepository;
import efub.SweetMeback.domain.oauth.service.OAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    private final OAuthService oAuthService;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberResponseDto getMember(){
        Member member = oAuthService.getCurrentMember();
        String nickname = member.getNickname();
        String email = member.getEmail();
        return new MemberResponseDto(nickname, email);
    }

    public void updateMemberNickname(MemberUpdateRequestDto requestDto){
        Member member = oAuthService.getCurrentMember();
        member.updateMemberNickname(requestDto.getNickname());
        memberRepository.save(member);
    }


}
