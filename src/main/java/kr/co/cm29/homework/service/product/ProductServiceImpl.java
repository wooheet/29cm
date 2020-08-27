package kr.co.cm29.homework.service.product;

import kr.co.cm29.homework.domain.Product;
import kr.co.cm29.homework.exception.NotFoundException;
import kr.co.cm29.homework.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void insertProduct() {
        ClassPathResource resource = new ClassPathResource("data/product.csv");

        try {
            Path path = Paths.get(resource.getURI());
            List<String> content = Files.readAllLines(path);

            for (int i = 1; i < content.size(); i++) {
                String[] productInfo = content.get(i).split(",");

                Product product = Product.builder()
                        .id(Long.parseLong(productInfo[0].trim()))
                        .name(extractionName(productInfo))
                        .price(Double.parseDouble(
                                productInfo[productInfo.length - 2].trim()))
                        .availableStock(Integer.parseInt(
                                productInfo[productInfo.length - 1].trim()))
                        .build();

                productRepository.save(product);
            }
        } catch (IOException e) {
            log.error("{}", e.getMessage(), e);
        }
    }

    private String extractionName(String[] productInfo) {
        String name;

        if (productInfo.length > 4) {
            List<String> prodInfoList = new ArrayList<>(Arrays.asList(productInfo));
            name = String.join(""
                    , prodInfoList.subList(1, prodInfoList.size() - 2)).trim();
        } else {
            name = productInfo[1].trim();
        }

        return name;
    }

    @Override
    public List<Product> getProductAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(long id) {
        return productRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }
}
