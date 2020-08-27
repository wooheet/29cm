package kr.co.cm29.homework.service.product;

import kr.co.cm29.homework.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    void insertProduct();

    List<Product> getProductAll();

    Product getProduct(long id);
}
