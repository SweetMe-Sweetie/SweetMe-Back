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
    private String merchantUid;
    private String detail;
    private Long amount;
    private String name;
    private String email;
    private boolean promotion;
    private LocalDateTime createdDate;

    public PaymentResponseDto(Payment payment) {
        this.postId = payment.getPost().getId();
        this.pg = payment.getPg();
        this.merchantUid = payment.getMerchantUid();
        this.detail = payment.getDetail();
        this.amount = payment.getAmount();
        this.name = payment.getName();
        this.email = payment.getEmail();
        this.promotion = payment.getPost().isPromotion();
        this.createdDate = payment.getCreatedDate();
    }
}
