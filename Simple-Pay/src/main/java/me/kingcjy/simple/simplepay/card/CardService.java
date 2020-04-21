package me.kingcjy.simple.simplepay.card;

import lombok.RequiredArgsConstructor;
import me.kingcjy.simple.simplepay.domain.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public Long addCard(CardDto.CardRequestDto cardRequestDto) {
        Card card = cardRequestDto.toEntity();
        cardRepository.save(card);

        return card.getId();
    }

    public List<CardDto.CardResponseDto> findCards() {

        DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = defaultOAuth2User.getAttribute("uid");

        List<Card> cards = cardRepository.findByUser(userRepository.findByEmail(email).get());

        return cards.stream().map(CardDto.CardResponseDto::new).collect(Collectors.toList());
    }
}
