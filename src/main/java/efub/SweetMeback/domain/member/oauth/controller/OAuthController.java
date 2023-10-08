package efub.SweetMeback.domain.member.oauth.controller;

import efub.SweetMeback.domain.member.oauth.dto.OAuthRequestDto;
import efub.SweetMeback.domain.member.oauth.dto.OAuthResponseDto;
import efub.SweetMeback.domain.member.oauth.service.OAuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/kakao")
public class OAuthController {

    private final OAuthService oAuthService;

    @ResponseBody
    @GetMapping("/login")
    public OAuthResponseDto login(@RequestBody OAuthRequestDto oAuthRequestDto) {
        return oAuthService.signIn(oAuthRequestDto.getCode());
    }
}
