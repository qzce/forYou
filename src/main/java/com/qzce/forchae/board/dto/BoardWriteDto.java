package com.qzce.forchae.board.dto;

import com.qzce.forchae.board.Board;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardWriteDto {

    private String title;

    private String content;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }

}
