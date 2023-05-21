package com.qzce.forchae.comment.dto;

import com.qzce.forchae.comment.Comment;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentWriteDto {

    private String bcContent;

    public Comment toEntity() {
        return Comment.builder()
                .bcContent(bcContent)
                .build();
    }

}
