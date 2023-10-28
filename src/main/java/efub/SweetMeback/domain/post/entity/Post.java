package efub.SweetMeback.domain.post.entity;

import efub.SweetMeback.domain.global.BaseTimeEntity;
import efub.SweetMeback.domain.member.entity.Member;
import javax.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "post")
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private Integer people;

    @Column(nullable = false)
    private Long view;

    @Column(nullable = false)
    private boolean recruitment;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "meeting", nullable = false)
    private Meeting meeting;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "contact", nullable = false)
    private Contact contact;

    @ManyToOne
    @JoinColumn(name = "member_id",nullable = false)
    private Member member;

    @Column(nullable = false)
    private boolean promotion;

    @Builder
    public Post(String title, String content, LocalDateTime deadline, LocalDateTime startDate
            , LocalDateTime endDate, Integer people, Long view, boolean recruitment
            , Category category, Meeting meeting, Contact contact, Member member, boolean promotion) {
        this.title = title;
        this.content = content;
        this.deadline = deadline;
        this.startDate = startDate;
        this.endDate = endDate;
        this.people = people;
        this.view = view;
        this.recruitment = recruitment;
        this.category = category;
        this.meeting = meeting;
        this.contact = contact;
        this.member = member;
        this.promotion = promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }
}
