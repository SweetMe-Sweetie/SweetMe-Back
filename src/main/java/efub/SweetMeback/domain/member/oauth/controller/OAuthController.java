package efub.SweetMeback.domain.member.oauth.controller;

import efub.SweetMeback.domain.member.oauth.service.OAuthService;
import efub.SweetMeback.domain.member.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/kakao")
public class OAuthController {

    private final OAuthService oAuthService;
    private final MemberService memberService;

    @ResponseBody
    @GetMapping("/login")
    public void login(@RequestParam("code") String code) {
        String access_Token = oAuthService.getKakaoAccessToken(code);
        HashMap<String, Object> userInfo = oAuthService.getUserInfo(access_Token);

        // HashMap에서 닉네임과 이메일을 가져옵니다.
        String nickname = (String) userInfo.get("nickname");
        String email = (String) userInfo.get("email");

        System.out.println("nickname: " + nickname + " email: "+ email);

        // createMember 메서드에 닉네임과 이메일을 전달하여 호출합니다.
        memberService.createMember(nickname, email);
    }
}
