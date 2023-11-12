package efub.SweetMeback.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private String nickname;
    private String email;
    private String profileImage;

    public MemberResponseDto(String nickname, String email, String profileImage){
        this.nickname = nickname;
        this.email = email;
        this.profileImage = profileImage;
    }
}
