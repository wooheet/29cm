package kr.co.cm29.homework.service.order;

import kr.co.cm29.homework.domain.Product;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Service
public interface OrderService {

  void order() throws IOException;

  void order(HashMap<Product, Integer> basket, Product product, int quantity);
}
