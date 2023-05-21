package com.qzce.forchae.comment;

import com.qzce.forchae.comment.dto.CommentUpdateDto;
import com.qzce.forchae.comment.dto.CommentWriteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/comment")
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<Comment> saveComment(CommentWriteDto commentWriteDto) {

        Comment savedComment = commentService.doSave(commentWriteDto.toEntity());

        return new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Comment> modifyComment(CommentUpdateDto commentUpdateDto) {

        Comment savedComment = commentService.doSave(commentUpdateDto.toEntity());

        return new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

    @DeleteMapping("/{bno}/{bcno}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long bno, @PathVariable Long bcno) {

        Comment comment = commentService.searchCommentOne(bno, bcno);

        commentService.doDelete(comment.getBcno());

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

}
