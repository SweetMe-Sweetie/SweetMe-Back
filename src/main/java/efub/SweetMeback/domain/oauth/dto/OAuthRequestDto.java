package efub.SweetMeback.domain.oauth.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OAuthRequestDto {
    private String code;
}
