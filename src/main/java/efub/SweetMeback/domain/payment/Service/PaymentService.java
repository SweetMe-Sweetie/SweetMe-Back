package efub.SweetMeback.domain.payment.Service;

import efub.SweetMeback.domain.member.entity.Member;
import efub.SweetMeback.domain.member.service.MemberService;
import efub.SweetMeback.domain.payment.dto.PaymentRequestDto;
import efub.SweetMeback.domain.payment.entity.Payment;
import efub.SweetMeback.domain.payment.repository.PaymentRepository;
import efub.SweetMeback.domain.post.entity.Post;
import efub.SweetMeback.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PostService postService;
    private final MemberService memberService;

    public Payment requestPayment(PaymentRequestDto requestDto) {
        Post post = postService.findPostById(requestDto.getPostId());
        Member member = memberService.findMemberById(requestDto.getMemberId());

        if (post.isPromotion() == true) {
            throw new IllegalArgumentException("홍보 중인 게시글입니다.");
        } else {
            post.setPromotion(true);
        }

        return paymentRepository.save(Payment.builder()
                        .post(post)
                        .pg("kakaopay.TC0ONETIME")
                        .merchant_uid("sweetme_" + new Date().getTime())
                        .name("스윗미 모집글 홍보비")
                        .amount(1000L)
                        .buyer_name(member.getName())
                        .buyer_email(member.getEmail())
                        .build());
    }
}
