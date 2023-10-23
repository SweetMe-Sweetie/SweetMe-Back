package efub.SweetMeback.domain.order.service;

import efub.SweetMeback.domain.order.dto.OrderRequestDto;
import efub.SweetMeback.domain.order.dto.OrderResponseDto;
import efub.SweetMeback.domain.order.entity.PaymentOrder;
import efub.SweetMeback.domain.order.repository.OrderRepository;
import efub.SweetMeback.domain.post.entity.Post;
import efub.SweetMeback.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PostService postService;

    public PaymentOrder createOrder(OrderRequestDto requestDto) {
        return orderRepository.save(PaymentOrder.builder()
                .post(postService.findPost(requestDto.getPostId()))
                .promotion(false)
                .build());
    }

    @Transactional(readOnly = true)
    public PaymentOrder findOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 주문번호입니다."));
    }
}
