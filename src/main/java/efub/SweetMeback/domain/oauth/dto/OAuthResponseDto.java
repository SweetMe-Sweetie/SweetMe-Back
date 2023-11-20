package efub.SweetMeback.domain.oauth.dto;

import efub.SweetMeback.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthResponseDto {

    private String nickname;
    private String email;
    private String accessToken;
    private String refreshToken;
    private boolean isfirst;

    @Builder
    public OAuthResponseDto(Member member, String accessToken, String refreshToken, boolean isfirst) {
        this.nickname = member.getNickname();
        this.email = member.getEmail();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.isfirst = isfirst;
    }
}
