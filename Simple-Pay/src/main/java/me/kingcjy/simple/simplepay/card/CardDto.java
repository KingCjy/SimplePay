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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Card card = Card.builder()
                    .cardNumber(cardNumber)
                    .cvc(cvc)
                    .password(password)
                    .expiredDate(LocalDate.parse(expiredDate, formatter))
                    .build();

            return card;
        }
    }

    @Data
    public static class CardResponseDto {
        private Long id;
        private String cardNumber;
        private String cvc;

        public CardResponseDto(Card card) {
            this.id = card.getId();
            this.cardNumber = card.getCardNumber();
            this.cvc = card.getCvc();
        }
    }
}
