package kr.co.cm29.homework.controller;

import kr.co.cm29.homework.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostConstruct
    private void setUp() {
        productService.insertProduct();
    }
}
