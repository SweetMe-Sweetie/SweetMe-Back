package efub.SweetMeback.domain.member.entity;

import jakarta.persistence.*;
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
    private Integer id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Builder
    public Member(String nickname, String email){
        this.nickname = nickname;
        this.email = email;
    }

    public void updateProfile(String nickname){
        this.nickname=nickname;
    }
}
