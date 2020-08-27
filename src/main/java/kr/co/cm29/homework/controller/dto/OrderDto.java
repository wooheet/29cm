package kr.co.cm29.homework.controller.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDto {
    @Data
    public static class Display {
        private long productId;
        private String name;
        private double price;
        private int Quantity;
    }

    @Data
    public static class Response {
        private double orderPrice;
        private double payments;
    }
}
