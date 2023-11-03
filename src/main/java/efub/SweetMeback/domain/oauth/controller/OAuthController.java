package efub.SweetMeback.domain.oauth.controller;

import efub.SweetMeback.domain.member.entity.Member;
import efub.SweetMeback.domain.oauth.dto.OAuthRequestDto;
import efub.SweetMeback.domain.oauth.dto.OAuthResponseDto;
import efub.SweetMeback.domain.oauth.service.OAuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Objects;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/kakao")
public class OAuthController {

    private final OAuthService oAuthService;

    @ResponseBody
    @PostMapping("/login")
    public OAuthResponseDto login(@RequestBody OAuthRequestDto oAuthRequestDto, HttpSession session) {
        OAuthResponseDto member = oAuthService.signIn(oAuthRequestDto.getCode());

        if (member.getEmail() != null) {
            session.setAttribute("userId", member.getEmail());
            session.setAttribute("access_Token", member.getAccessToken());
        }

        return member;
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
}
