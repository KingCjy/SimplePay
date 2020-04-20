package me.kingcjy.simple.simplepay.carc;

import me.kingcjy.simple.simplepay.card.CardDto;
import me.kingcjy.simple.simplepay.card.CardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.spec.PSource;

@SpringBootTest
@Transactional
public class CardServiceTest {

    @Autowired
    private CardService cardService;

    @Test
    public void 카드_추가() throws Exception {
        CardDto.CardRequestDto cardRequestDto = new CardDto.CardRequestDto();
        cardRequestDto.setCardNumber("1002100991070323");
        cardRequestDto.setCvc("282");
        cardRequestDto.setExpiredDate("2023-08-01");
        cardRequestDto.setPassword("12");

        Long cardId = cardService.addCard(cardRequestDto);

        System.out.println(cardId);
        Assertions.assertNotNull(cardId);
    }
}
