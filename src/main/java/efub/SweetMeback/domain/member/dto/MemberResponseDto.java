package efub.SweetMeback.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private String nickname;
    private String email;

    public MemberResponseDto(String nickname, String email){
        this.nickname = nickname;
        this.email = email;
    }
}
