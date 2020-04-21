package me.kingcjy.simple.simplepay.card;

import lombok.RequiredArgsConstructor;
import me.kingcjy.simple.simplepay.util.ApiResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardApiController {

    private final CardService cardService;

    @PostMapping
    public ApiResult<?> addCard(@RequestBody CardDto.CardRequestDto cardRequestDto) {
        Long cardId = cardService.addCard(cardRequestDto);
        return ApiResult.successResponse(cardId);
    }

    @GetMapping
    public ApiResult<?> findCards() {
        return ApiResult.successResponse(cardService.findCards());
    }
}
