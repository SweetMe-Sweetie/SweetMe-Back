package efub.SweetMeback.domain.payment.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentRequestDto {
    private Long postId;
    private Long memberId;
}
