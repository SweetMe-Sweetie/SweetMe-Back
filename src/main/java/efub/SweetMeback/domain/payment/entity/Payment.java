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

    private String merchant_uid;

    private String name;

    private Long amount;

    private String buyer_name;

    private String buyer_email;

    @Builder
    public Payment(Post post, String pg, String merchant_uid, String name, Long amount, String buyer_name, String buyer_email) {
        this.post = post;
        this.pg = pg;
        this.merchant_uid = merchant_uid;
        this.name = name;
        this.amount = amount;
        this.buyer_name = buyer_name;
        this.buyer_email = buyer_email;
    }
}
