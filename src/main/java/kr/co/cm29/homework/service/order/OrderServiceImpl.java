package kr.co.cm29.homework.service.order;

import kr.co.cm29.homework.domain.Basket;
import kr.co.cm29.homework.domain.Product;
import kr.co.cm29.homework.repository.BasketRepository;
import kr.co.cm29.homework.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final int fee = 5000;
    private final ProductService productService;
    private final BasketRepository basketRepository;
    private static HashMap<Product, Integer> basket;

    @Override
    public void order() {
        basket = new HashMap<>();
        System.out.print("입력(o[order]: 주문, q[quit]: 종료) : ");
//        new Scanner(String.valueOf(System.in.available()));
        Scanner scanner = new Scanner(System.in);

        String selectOder = scanner.nextLine();

        if (selectOder.equals("o") || selectOder.equals("order")) {
            orderDisplay(productService.getProductAll());

            while (true) {
                System.out.print("상품번호: ");
                String prodNum = scanner.nextLine();

                if (prodNum.compareTo(" ") == 0) {
                    if (resultDisplay()) {
                        order();
                        break;
                    } else
                        continue;
                }

                System.out.print("수량: ");
                String quantity = scanner.nextLine();

                Product selectProduct = productService.getProduct(Long.parseLong(prodNum));

                order(basket, selectProduct, Integer.parseInt(quantity));
            }
        } else if (selectOder.equals("q") || selectOder.equals("quit")){
            System.out.println("고객님의 주문 감사합니다.");
        } else {
            System.out.println("o 또는 q를 입력해주세요.");
        }

        scanner.close();
    }

    @Override
    public void order(HashMap<Product, Integer> basket, Product product, int quantity) {
        if (basket.containsKey(product)) {
            int temQuantity = basket.get(product);
            int insertQuantity = quantity + temQuantity;

            insertBasket(basket, product, insertQuantity);
        } else {
            insertBasket(basket, product, quantity);
        }
    }

    private boolean quantityValidate(Product product, int insertQuantity) {
        return product.getAvailableStock() >= insertQuantity;
    }

    private void insertBasket(HashMap<Product, Integer> basket, Product product, int quantity) {
        if (quantityValidate(product, quantity)) {
            basket.put(product, quantity);
            basketRepository.save(Basket.builder()
                    .productId(product.getId())
                    .quantity(quantity)
                    .build());
        } else {
            System.out.println("SoldOutException 발생. 주무한 상품량이 재고량보다 큽니다. ");
        }
    }

    private double calPayment(double price, int quantity) {
        return price * quantity;
    }

    private void orderDisplay(List<Product> productList) {
        System.out.println("상품번호 \t\t 상품명 \t\t\t\t\t\t\t 판매가격 \t\t 재고수");

        productList.stream().sorted(Comparator.comparingLong(Product::getId).reversed())
                .forEach(product -> System.out.println(product.getId() + "       " + product.getName() + "     "
                        + product.getPrice() + "      " + product.getAvailableStock()));
    }

    private boolean resultDisplay() {
        double payment = 0;
        boolean check = false;

        if (basket.keySet().size() == 0) return false;

        for (Product product : basket.keySet()) {
            int quantity = basket.get(product);

            if (!quantityValidate(product, quantity)) {
                System.out.println("SoldOutException 발생. 주무한 상품량이 재고량보다 큽니다. ");
                return false;
            }

            if (!check) {
                System.out.println("주문내역:");
                System.out.println("------------------------------------");
                check = true;
            }

            System.out.println(product.getName() + " - " + quantity + "개");

            payment +=calPayment(product.getPrice(), quantity);
        }

        System.out.println("------------------------------------");

        System.out.println("주문금액: "
                + NumberFormat.getCurrencyInstance(Locale.KOREA).format(payment) + "원");

        if (payment < 50000) {
            System.out.println("배송비: "
                    + NumberFormat.getCurrencyInstance(Locale.KOREA).format(fee));
            payment += fee;
        }

        System.out.println("------------------------------------");
        System.out.println("지불금액: "
                + NumberFormat.getCurrencyInstance(Locale.KOREA).format(payment) + "원");

        System.out.println("------------------------------------");

        return true;
    }

}
