package kr.co.cm29.homework;

import kr.co.cm29.homework.service.order.OrderService;
import kr.co.cm29.homework.service.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class OrderTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
//        Collection<UserRoleType> userRoleTypes = Sets.newHashSet(
//                UserRoleType.USER);
//
//        User savedUser = userRepository.save(User.builder()
//                .email("admin@test.com")
//                .password(passwordEncoder.encode("1234567")).build());
//
//        for (UserRoleType roleType : userRoleTypes) {
//            savedUser.addRole(roleType);
//        }

    }

    @Test
    public void issue() {
//        Vacation vacation = vacationService.issue(1, tomorrow, AnnualType.ANNUAL, tomorrow2, "ok");
//
//        assert vacation.getRemainAnnualDay() == 14;
    }
}
