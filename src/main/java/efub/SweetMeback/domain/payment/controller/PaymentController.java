package efub.SweetMeback.domain.payment.controller;

import efub.SweetMeback.domain.payment.Service.PaymentService;
import efub.SweetMeback.domain.payment.dto.PaymentRequestDto;
import efub.SweetMeback.domain.payment.dto.PaymentResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDto> requestPayment(@RequestBody PaymentRequestDto requestDto) {
        return new ResponseEntity<>(new PaymentResponseDto(paymentService.requestPayment(requestDto)), HttpStatus.CREATED);
    }
}
