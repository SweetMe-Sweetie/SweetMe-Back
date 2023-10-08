package efub.SweetMeback.domain.member.oauth.dto;

import efub.SweetMeback.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthResponseDto {

    private String nickname;
    private String email;
    private String accessToken;
    private String refreshToken;

    @Builder
    public OAuthResponseDto(Member member, String accessToken, String refreshToken) {
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}