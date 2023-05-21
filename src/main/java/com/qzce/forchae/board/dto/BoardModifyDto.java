package com.qzce.forchae.board.dto;


import com.qzce.forchae.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
public class BoardModifyDto {

    private Long bno;

    private String title;

    private String content;

    private LocalDateTime writeDate;

    public Board toEntity() {
        return Board.builder()
                .bno(bno)
                .title(title)
                .content(content)
                .build();
    }

}
