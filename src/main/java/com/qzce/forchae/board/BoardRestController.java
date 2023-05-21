package com.qzce.forchae.board;

import com.qzce.forchae.board.dto.BoardListDto;
import com.qzce.forchae.board.dto.BoardModifyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/board")
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardService boardService;


    @GetMapping("")
    public Page<BoardListDto> boardAll(@PageableDefault(size=5, sort = "bno", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Board> list = boardService.searchBoardList(pageable);

        Page<BoardListDto> boardList = list.map(BoardListDto::new);

        return boardList;
    }

    @GetMapping("/{bno}")
    public Board doSearchOne(@PathVariable Long bno) {

        Board board = boardService.searchBoard(bno).orElseThrow(() -> new NullPointerException("no exist"));

        return board;
    }

    @PostMapping("")
    public Board doSaveOne(Board board) {
        return boardService.saveBoard(board);
    }

    @PutMapping("")
    public Board doModifyOne(BoardModifyDto boardModifyDto) {
//        return boardService.saveBoard(boardModifyDto);
        return null;
    }

}
