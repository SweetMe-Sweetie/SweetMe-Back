package efub.SweetMeback.domain.member.service;

import efub.SweetMeback.domain.member.entity.Member;
import efub.SweetMeback.domain.member.oauth.service.OAuthService;
import efub.SweetMeback.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final OAuthService oAuthService;

    @Transactional
    public void createMember(String nickname, String email){
        Optional<Member> existMember = memberRepository.findByEmail(email);

        if(existMember.isPresent()) {
            throw new IllegalArgumentException("이미 가입한 계정입니다.");
        }
        else{
            Member member = Member.builder()
                    .nickname(nickname)
                    .email(email)
                    .build();
            memberRepository.save(member);
        }
    }

    @Transactional(readOnly = true)
    public Member getMemberById(Integer member_id){
        return memberRepository.findById(member_id).orElseThrow(() -> new EntityNotFoundException("해당 id 를 가진 Member를 찾을 수 없습니다. id ="+member_id));
    }
}
