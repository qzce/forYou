package com.qzce.forchae.board;

import com.qzce.forchae.board.dto.BoardListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 전체조회
    public Page<Board> searchBoardList(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }

    // 조회
    public Optional<Board> searchBoard(Long bno) {
        return boardRepository.findById(bno);
    }

    // 저장, 수정
    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    // 삭제
    public void deleteBoard(Long bno) {
        boardRepository.deleteById(bno);
    }


}
