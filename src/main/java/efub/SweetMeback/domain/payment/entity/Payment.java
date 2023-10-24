package efub.SweetMeback.domain.payment.entity;

import efub.SweetMeback.domain.global.BaseTimeEntity;
import efub.SweetMeback.domain.post.entity.Post;
import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "payment")
public class Payment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    private String pg;

    @Column(name = "merchant_uid")
    private String merchantUid;

    private String detail;

    private Long amount;

    private String name;

    private String email;

    @Builder
    public Payment(Post post, String pg, String merchantUid, String detail, Long amount, String name, String email) {
        this.post = post;
        this.pg = pg;
        this.merchantUid = merchantUid;
        this.detail = detail;
        this.amount = amount;
        this.name = name;
        this.email = email;
    }
}
