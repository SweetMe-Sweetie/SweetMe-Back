package efub.SweetMeback.domain.payment.repository;

import efub.SweetMeback.domain.payment.entity.Payment;
import efub.SweetMeback.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByPost(Post post);
}
