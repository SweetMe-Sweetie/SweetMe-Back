package efub.SweetMeback.domain.order.dto;

import efub.SweetMeback.domain.order.entity.PaymentOrder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderResponseDto {
    private UUID orderId;
    private Long postId;
    private String nickname;
    private boolean promotion;
    private LocalDateTime createdDate;

    public OrderResponseDto(PaymentOrder order) {
        this.orderId = order.getOrderId();
        this.postId = order.getPost().getId();
        this.nickname = order.getPost().getMember().getNickname();
        this.promotion = order.isPromotion();
        this.createdDate = order.getCreatedDate();
    }
}
