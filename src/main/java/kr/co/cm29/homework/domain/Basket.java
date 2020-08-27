package kr.co.cm29.homework.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Basket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basket_id")
    private long id;

    private long productId;

    private int quantity;

    @CreationTimestamp
    private LocalDateTime createAt;

    @Builder
    public Basket(long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
