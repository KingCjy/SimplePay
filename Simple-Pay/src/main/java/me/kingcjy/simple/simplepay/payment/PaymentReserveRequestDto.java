package me.kingcjy.simple.simplepay.payment;

import lombok.Data;

@Data
public class PaymentReserveRequestDto {
    private String userKey;
    private String orderKey;
//    대표 이름
    private String productName;
    private Integer totalPayAmount;
}
