package kr.co.cm29.homework;

import kr.co.cm29.homework.domain.Basket;
import kr.co.cm29.homework.domain.Product;
import kr.co.cm29.homework.repository.BasketRepository;
import kr.co.cm29.homework.service.order.OrderService;
import kr.co.cm29.homework.service.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class OrderTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        productService.insertProduct();
    }

    @Test
    public void orderTest() throws InterruptedException {
        Product product = productService.getProductAll().stream().findFirst().get();

        HashMap<Product, Integer> basket = new HashMap<>();

        orderService.order(basket, product, 1);

        Basket repoBasket = basketRepository.findByProductId(product.getId()).get();

        assert repoBasket.getProductId() == product.getId();
    }

    @Test
    public void orderSoldOutTest() throws InterruptedException {
        Product product = productService.getProductAll().stream().findFirst().get();

        HashMap<Product, Integer> basket = new HashMap<>();

        orderService.order(basket, product, 100000);

        Basket repoBasket = basketRepository.findByProductId(product.getId()).orElse(null);

        assert repoBasket == null;
    }

    @Test
    public void thread() throws InterruptedException {
        int[] quantity = {1, 1000 };

//        CountDownLatch cl = new CountDownLatch(3);

        for (int i = 0; i < 2; i++) {
            List<Product> products = productService.getProductAll();

            HashMap<Product, Integer> basket = new HashMap<>();

            orderService.order(basket, products.get(i), quantity[i]);

            Basket repoBasket = basketRepository.findByProductId(products.get(i).getId()).orElse(null);

            assert i ==1 ? repoBasket == null : repoBasket.getProductId() == products.get(0).getId();
        }

        Thread.sleep(3*1000);
    }
}
