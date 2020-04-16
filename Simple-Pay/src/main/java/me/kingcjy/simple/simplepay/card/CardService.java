package me.kingcjy.simple.simplepay.card;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public void addCard(CardDto.CardRequestDto cardRequestDto) {

    }
}
