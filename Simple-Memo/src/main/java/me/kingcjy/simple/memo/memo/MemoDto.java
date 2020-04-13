package me.kingcjy.simple.memo.memo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MemoDto {

    @Data
    public static class MemoRequestDto {
        @NotNull
        @NotEmpty
        private String contents;
    }

    @Data
    public static class MemoResponseDto {
        private Long id;
        private String contents;
        private LocalDateTime createdDateTime;
        private LocalDateTime updatedDateTime;

        public MemoResponseDto(Memo memo) {
            this.id = memo.getId();
            this.contents = memo.getContents();
            this.createdDateTime = memo.getCreatedDateTime();
            this.updatedDateTime = memo.getUpdatedDateTime();
        }
    }
}
