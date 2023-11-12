package efub.SweetMeback.domain.member.entity;

import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String profileImage;

    @Builder
    public Member(String name, String nickname, String email, String profileImage){
        this.name=name;
        this.nickname = nickname;
        this.email = email;
        this.profileImage = profileImage;
    }

    public void updateMemberNickname(String nickname){
        this.nickname = nickname;
    }
}
