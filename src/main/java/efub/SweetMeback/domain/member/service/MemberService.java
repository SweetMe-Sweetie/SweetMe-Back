package efub.SweetMeback.domain.member.service;

import efub.SweetMeback.domain.member.dto.MemberResponseDto;
import efub.SweetMeback.domain.member.entity.Member;
import efub.SweetMeback.domain.member.oauth.service.OAuthService;
import efub.SweetMeback.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final OAuthService oAuthService;

    @Transactional
    public void createMember(String nickname, String email){
        Member member = Member.builder()
                .nickname(nickname)
                .email(email)
                .build();

        memberRepository.save(member);
    }

    @Transactional(readOnly = true)
    public MemberResponseDto getMember(){
        Member member = oAuthService.getCurrentMember();
        String nickname = member.getNickname();
        String email = member.getEmail();
        return new MemberResponseDto(nickname, email);
    }
}
