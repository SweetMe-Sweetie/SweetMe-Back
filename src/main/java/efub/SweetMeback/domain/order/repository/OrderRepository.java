package efub.SweetMeback.domain.order.repository;

import efub.SweetMeback.domain.order.entity.PaymentOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<PaymentOrder, UUID> {
}
