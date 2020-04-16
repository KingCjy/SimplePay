package me.kingcjy.simple.simplepay.card;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CardDto {

    @Data
    public static class CardRequestDto {
        private String cardNumber;
        private String expiredDate;
        private String cvc;
        private String password;

        public Card toEntity() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMyyyy");;
            Card card = Card.builder()
                    .cardNumber(cardNumber)
                    .cvc(cvc)
                    .password(password)
                    .expiredDate(LocalDate.parse(expiredDate))
                    .build();

            return card;
        }
    }
}
