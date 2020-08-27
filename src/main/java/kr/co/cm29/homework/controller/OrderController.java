package kr.co.cm29.homework.controller;

import kr.co.cm29.homework.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @EventListener(ApplicationReadyEvent.class)
    public void order() throws IOException {
        orderService.order();
    }
}
