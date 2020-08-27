package kr.co.cm29.homework.config;

import kr.co.cm29.homework.domain.Basket;
import kr.co.cm29.homework.domain.Product;
import kr.co.cm29.homework.repository.BasketRepository;
import kr.co.cm29.homework.repository.ProductRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackageClasses = {
        Product.class,
        Basket.class
})
@EnableJpaRepositories(basePackageClasses = {
        ProductRepository.class,
        BasketRepository.class
})
public class DatabaseConfig {}
