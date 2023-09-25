package efub.SweetMeback.domain.heart.entity;

import efub.SweetMeback.domain.member.entity.Member;
import efub.SweetMeback.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "heart")
public class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "heart_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;

}
