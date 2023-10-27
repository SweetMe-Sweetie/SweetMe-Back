package efub.SweetMeback.domain.payment.dto;

import efub.SweetMeback.domain.payment.entity.Payment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentResponseDto {
    private Long postId;
    private String pg;
    private String merchant_uid;
    private String name;
    private Long amount;
    private String buyer_name;
    private String buyer_email;
    private boolean promotion;
    private LocalDateTime createdDate;

    public PaymentResponseDto(Payment payment) {
        this.postId = payment.getPost().getId();
        this.pg = payment.getPg();
        this.merchant_uid = payment.getMerchant_uid();
        this.name = payment.getName();
        this.amount = payment.getAmount();
        this.buyer_name = payment.getBuyer_name();
        this.buyer_email = payment.getBuyer_email();
        this.promotion = payment.getPost().isPromotion();
        this.createdDate = payment.getCreatedDate();
    }
}
