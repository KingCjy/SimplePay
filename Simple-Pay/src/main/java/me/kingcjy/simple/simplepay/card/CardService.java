package me.kingcjy.simple.simplepay.card;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public Long addCard(CardDto.CardRequestDto cardRequestDto) {
        Card card = cardRequestDto.toEntity();
        cardRepository.save(card);

        return card.getId();
    }

    public List<CardDto.CardResponseDto> findCards() {
        SecurityContextHolder.getContext().getAuthentication();

        return null;
    }
}
