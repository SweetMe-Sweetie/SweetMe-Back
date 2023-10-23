package efub.SweetMeback.domain.order.entity;

import efub.SweetMeback.domain.global.BaseTimeEntity;
import efub.SweetMeback.domain.post.entity.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "payment_order")
public class PaymentOrder extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    @Column(name = "order_id")
    private UUID orderId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(columnDefinition = "false")
    private boolean promotion;

    @Builder
    public PaymentOrder(UUID orderId, Post post, boolean promotion) {
        this.orderId = orderId;
        this.post = post;
        this.promotion = promotion;
    }
}
