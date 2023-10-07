package efub.SweetMeback.domain.payment.entity;

import efub.SweetMeback.domain.post.entity.Post;
import javax.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;
}
