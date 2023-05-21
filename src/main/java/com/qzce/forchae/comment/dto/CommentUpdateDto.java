package com.qzce.forchae.comment.dto;

import com.qzce.forchae.comment.Comment;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentUpdateDto {

    private Long bno;
    private Long bcno;
    private String bcContent;

    public Comment toEntity() {
        return Comment.builder()
                .bno(bno)
                .bcno(bcno)
                .bcContent(bcContent)
                .build();
    }
}
