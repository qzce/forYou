package com.qzce.forchae.board;

import com.qzce.forchae.board.dto.BoardListDto;
import com.qzce.forchae.board.dto.BoardModifyDto;
import com.qzce.forchae.board.dto.BoardWriteDto;
import com.qzce.forchae.comment.Comment;
import com.qzce.forchae.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("")
    public String boardList(Model model,
                            @PageableDefault(page = 0, size = 10, sort = "bno", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Board> list = boardService.searchBoardList(pageable);

        Page<BoardListDto> boardListDtos = list.map(BoardListDto::new);

        final int pageRange = 10;

        int nowPage = list.getPageable().getPageNumber();   // 현재 페이지
        int fitPage = (nowPage-1)/pageRange;                // 페이지 맞춤

        int startPage = fitPage * pageRange + 1;            // 시작 페이지
        int endPage = fitPage * pageRange + pageRange;      // 끝 페이지

        int totalPage = list.getTotalPages();               // 총 페이지

        int lastStartPage = totalPage / pageRange * pageRange + 1;

        if(endPage > totalPage) {
            endPage = totalPage;
        }

        model.addAttribute("boardList", boardListDtos);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("lastStartPage", lastStartPage);
        model.addAttribute("pageRange", pageRange);

        return "boardList";
    }

    @GetMapping("/{bno}")
    public String boardView(Model model, @PathVariable("bno") Long bno) {

        Board board = boardService.searchBoard(bno).orElseThrow(() -> new NullPointerException("null"));

        List<Comment> commentList = commentService.searchComment(bno);

        model.addAttribute("bno", bno);
        model.addAttribute("board", board);
        model.addAttribute("commentList", commentList);

        return "boardView";
    }

    @GetMapping("/write")
    public String boardWrite(Model model) {
        return "boardWrite";
    }

    @GetMapping("/modify/{bno}")
    public String boardModify(Model model, @PathVariable("bno") Long bno) {

        Board board = boardService.searchBoard(bno).orElseThrow(() -> new NullPointerException("null"));

        model.addAttribute("bno", bno);
        model.addAttribute("board", board);

        return "boardModify";
    }

    @PostMapping("")
    public String doWrite(BoardWriteDto boardWriteDto) {
        boardService.saveBoard(boardWriteDto.toEntity());
        return "redirect:/board";
    }

    @PutMapping("")
    public String doModify(BoardModifyDto boardModifyDto) {
        boardService.saveBoard(boardModifyDto.toEntity());
        return "redirect:/board";
    }

    @DeleteMapping("/{bno}")
    public String doDelete(@PathVariable("bno") Long bno) {
        boardService.deleteBoard(bno);
        return "redirect:/board";
    }
}
