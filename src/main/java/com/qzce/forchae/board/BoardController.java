package com.qzce.forchae.board;

import com.qzce.forchae.board.dto.BoardModifyDto;
import com.qzce.forchae.board.dto.BoardWriteDto;
import com.qzce.forchae.comment.Comment;
import com.qzce.forchae.comment.CommentService;
import com.qzce.forchae.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

    @GetMapping("")
    public String boardList(Model model,
                            @PageableDefault(size = 10, sort = "bno", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Board> list = boardService.searchBoardList(pageable);

        int pageRange = list.getSize();

        int nowPage = list.getPageable().getPageNumber();   // 현재 페이지
        int nowPageAddSize = nowPage + pageRange;

        int fitPage = nowPage/pageRange;                // 페이지 맞춤

        int startPage = fitPage * pageRange + 1;            // 시작 페이지
        int endPage = fitPage * pageRange + pageRange;      // 끝 페이지

        int totalPage = list.getTotalPages();               // 총 페이지

        int lastStartPage = totalPage / pageRange * pageRange + 1;

        if(endPage > totalPage) {
            endPage = totalPage;
        }

        model.addAttribute("boardList", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("nowPageAddSize", nowPageAddSize);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("lastStartPage", lastStartPage);
        model.addAttribute("pageRange", pageRange);
        model.addAttribute("pageable", pageable);

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
    public String boardWrite() {
        return "boardWrite";
    }

    @GetMapping("/modify/{bno}")
    public String boardModify(Model model, @PathVariable("bno") Long bno, Principal principal) {

        Board board = boardService.searchBoard(bno).orElseThrow(() -> new NullPointerException("null"));

        boolean sameId = validateId(principal, board);

        if(!sameId) {
            model.addAttribute("message", "다른사람의 글");
            model.addAttribute("searchUrl", "/board/"+bno);
            return "message";
        }

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
    public String doDelete(Model model, @PathVariable("bno") Long bno, Principal principal) {

        Board board = boardService.searchBoard(bno).orElseThrow(() -> new NullPointerException("null"));

        boolean sameId = validateId(principal, board);

        if(!sameId) {
            model.addAttribute("message", "다른사람의 글");
            model.addAttribute("searchUrl", "/board/"+bno);
            return "message";
        }

        boardService.deleteBoard(bno);
        return "redirect:/board";
    }

    // 같은 아이디?
    public boolean validateId(Principal principal, Board board) {

        if(board.getWriteId() != null && principal != null) {
            String loginId = principal.getName();
            String writeId = board.getWriteId();
            return loginId.equals(writeId);
        }
        return false;
    }


}
