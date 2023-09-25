package efub.SweetMeback.domain.post.entity;

import efub.SweetMeback.domain.global.BaseTimeEntity;
import efub.SweetMeback.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "post")
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer id;

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
    private Integer view;

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
}
