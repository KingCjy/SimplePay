package me.kingcjy.simple.simplepay.card;

import lombok.RequiredArgsConstructor;
import me.kingcjy.simple.simplepay.util.ApiResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardApiController {

    private final CardService cardService;

    @PostMapping
    public ApiResult<?> addCard(@RequestBody CardDto.CardRequestDto cardRequestDto) {
        cardService.addCard(cardRequestDto);
        return ApiResult.successResponse();
    }
}
