package com.qzce.forchae.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    // 조회
    public List<Comment> searchComment(Long bno) {
        return commentRepository.findByBno(bno);
    }

    // 조회 1
    public Comment searchCommentOne(Long bno, Long bcno) {
        return commentRepository.findByBnoAndBcno(bno, bcno).orElseThrow(() -> new NullPointerException("null.."));
    }

    // 등록, 수정
    public Comment doSave(Comment comment) {
        return commentRepository.save(comment);
    }

    // 삭제
    public void doDelete(Long bcno) {
        commentRepository.deleteById(bcno);
    }

}
