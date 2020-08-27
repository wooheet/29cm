package kr.co.cm29.homework.repository;

import kr.co.cm29.homework.domain.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    Optional<Basket> findByProductId(long productId);
}
