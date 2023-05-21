package com.qzce.forchae.board.dto;

import com.qzce.forchae.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardListDto {

    private Long bno;

    private String title;

    private String content;

    private LocalDateTime writeDate;

    private String writeId;

    private LocalDateTime modifyDate;

    private String modifyId;

    public BoardListDto(Board board) {
        this.bno = board.getBno();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writeDate = board.getWriteDate();
        this.writeId = board.getWriteId();
        this.modifyDate = board.getModifyDate();
        this.modifyId = board.getModifyId();
    }
}
