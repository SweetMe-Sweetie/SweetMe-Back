package efub.SweetMeback.domain.oauth.controller;

import efub.SweetMeback.domain.member.entity.Member;
import efub.SweetMeback.domain.member.service.MemberService;
import efub.SweetMeback.domain.oauth.dto.OAuthRequestDto;
import efub.SweetMeback.domain.oauth.dto.OAuthResponseDto;
import efub.SweetMeback.domain.oauth.service.OAuthService;
import efub.SweetMeback.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/kakao")
public class OAuthController {

    private final OAuthService oAuthService;
    private final JwtProvider jwtProvider;

    private final MemberService memberService;

    @ResponseBody
    @PostMapping("/login")
    public OAuthResponseDto login(@RequestBody OAuthRequestDto oAuthRequestDto, HttpSession session) {
        String kakaoToken = oAuthService.getKakaoAccessToken(oAuthRequestDto.getCode());
        Member member = oAuthService.getUserInfo(kakaoToken);

        String accessToken = jwtProvider.createAccessToken(member.getId());
        String refreshToken = jwtProvider.createRefreshToken(member.getId());

        if (member.getEmail() != null) {
            session.setAttribute("userId", member.getEmail());
            session.setAttribute("access_Token", kakaoToken);
        }

        return OAuthResponseDto.builder()
                .member(member)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .isfirst(oAuthService.isFirst)
                .build();
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        String access_Token = (String)session.getAttribute("access_Token");
        System.out.println(access_Token);

        if(access_Token != null && !"".equals(access_Token)){
            oAuthService.logout(access_Token);
            session.removeAttribute("access_Token");
            session.removeAttribute("userId");
            System.out.println("logout success");
        }else{
            System.out.println("access_Token is null");
        }

        return "redirect:/";
    }


    @DeleteMapping("/unlink")
    public String unlink(HttpSession session){
        // 세션에서 사용자 정보를 가져옴
        String access_Token = (String)session.getAttribute("access_Token");
        Member member = oAuthService.getCurrentMember();

        if(access_Token != null && !"".equals(access_Token)){
            oAuthService.unlink(access_Token);
            memberService.deleteMember(member);

            session.removeAttribute("access_Token");
            session.removeAttribute("userId");
            System.out.println("unlink success");
        }else{
            System.out.println("access_Token is null");
        }
        return "redirect:/";
    }
}
