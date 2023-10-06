package efub.SweetMeback.domain.member.dto;

import efub.SweetMeback.domain.member.entity.Member;
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

    public static MemberResponseDto from(Member member){
        return new MemberResponseDto(member.getNickname(), member.getEmail());
    }
}
