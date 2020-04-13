package me.kingcjy.simple.memo.memo;

import lombok.RequiredArgsConstructor;
import me.kingcjy.simple.memo.util.ApiResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/memos")
@RequiredArgsConstructor
public class MemoApiController {

    private final MemoService memoService;

    @GetMapping
    public ApiResult<?> getMemos() {
        return ApiResult.successResponse(memoService.getMemos());
    }

    @PostMapping
    public ApiResult<?> addMemo(@RequestBody @Valid MemoDto.MemoRequestDto dto) {
        Long id = memoService.addMemo(dto);
        return ApiResult.successResponse(id);
    }

    @DeleteMapping("/{id}")
    public ApiResult<?> deleteMemo(@PathVariable Long id) {
        memoService.deleteMemo(id);
        return ApiResult.successResponse();
    }
}