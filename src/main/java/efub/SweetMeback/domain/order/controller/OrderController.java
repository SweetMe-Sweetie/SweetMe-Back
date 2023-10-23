package efub.SweetMeback.domain.order.controller;

import efub.SweetMeback.domain.order.dto.OrderRequestDto;
import efub.SweetMeback.domain.order.dto.OrderResponseDto;
import efub.SweetMeback.domain.order.entity.PaymentOrder;
import efub.SweetMeback.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto requestDto) {
        return new ResponseEntity<>(new OrderResponseDto(orderService.createOrder(requestDto)), HttpStatus.CREATED);
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<OrderResponseDto> findOrderById(@PathVariable UUID order_id) {
        return ResponseEntity.ok(new OrderResponseDto(orderService.findOrderById(order_id)));
    }
}
