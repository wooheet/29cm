package kr.co.cm29.homework.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @Column(name = "product_id")
    private long id;

    private String name;

    private double price;

    @Builder.Default
    private int availableStock = 0;

    public void addAvailableStock(int quantity) {
        this.availableStock += quantity;
    }

    public void subtractAvailableStock(int quantity) {
        this.availableStock -= quantity;
    }
}
