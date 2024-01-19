package com.collaborateam.www.controller;

import com.collaborateam.www.domain.BoardDto;
import com.collaborateam.www.domain.BoardListCondition;
import com.collaborateam.www.domain.Pagination;
import com.collaborateam.www.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping("/list")
    public String list(BoardListCondition blc, Model model, HttpServletRequest request) {
        if(!loginCheck(request))    // If not logged in
            return "redirect:/login/login?redirectUrl="+request.getRequestURL();    // Redirect to the login page

        try {
            int rowCnt = boardService.getSearchResultCnt(blc);

            if(rowCnt == 0)
                throw new Exception("Board list load failed");

            Pagination pagination = new Pagination(rowCnt, blc);
            List<BoardDto> list = boardService.getSearchResultPage(blc);

            model.addAttribute("list", list);
            model.addAttribute("pagination", pagination);
        } catch (Exception e) {
            model.addAttribute("msg", "BOARD_LIST_LOAD_ERR");
    }
        return "boardList";
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("mode", "WRT_BOARD");
        return "board";
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, Model model, RedirectAttributes rattr, HttpSession session) {
        String writer = (String)session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            int rowCnt = boardService.write(boardDto);

            if(rowCnt !=1)
                throw new Exception("Board write failed");

            rattr.addFlashAttribute("msg", "BOARD_WRT_OK");
            return "redirect:/board/list";
        } catch (Exception e) {
            model.addAttribute("boardDto", boardDto);
            model.addAttribute("msg", "BOARD_WRT_ERR");
            return "board";
        }
    }

    @GetMapping("/read")
    public String read(Integer bno, BoardListCondition blc, Model model, RedirectAttributes rattr) {
        try {
            BoardDto boardDto = boardService.read(bno);

            if(boardDto == null)
                throw new Exception("Board load failed");

            model.addAttribute("boardDto", boardDto);
            model.addAttribute("mode", "READ_BOARD");
            return "board";
        } catch (Exception e) {
            rattr.addFlashAttribute("msg", "BOARD_LOAD_ERR");
            return "redirect:/board/list"+blc.getQueryString();
        }
    }

    @PostMapping("/modify")
    public String modify(BoardDto boardDto, BoardListCondition blc, Model model, RedirectAttributes rattr, HttpSession session) {
        String writer = (String)session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            int rowCnt = boardService.modify(boardDto);

            if(rowCnt != 1)
                throw new Exception("Board modify failed");

            rattr.addFlashAttribute("msg", "BOARD_MOD_OK");
            return "redirect:/board/list"+blc.getQueryString();
        } catch (Exception e) {
            model.addAttribute("boardDto", boardDto);
            model.addAttribute("msg", "BOARD_MOD_ERR");
            return "board";
        }
    }

    @PostMapping("/remove")
    public String remove(Integer bno, BoardListCondition blc, RedirectAttributes rattr, HttpSession session) {
        String writer = (String)session.getAttribute("id");

        try {
            int rowCnt = boardService.remove(bno, writer);

            if(rowCnt != 1)
                throw new Exception("Board delete failed");

            rattr.addFlashAttribute("msg", "BOARD_DEL_OK");
            return "redirect:/board/list"+blc.getQueryString();
        } catch (Exception e) {
            rattr.addFlashAttribute("msg", "BOARD_DEL_ERR");
            return "boardList";
        }
    }

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("id")!=null;    // If "id" exists in the session, return true
    }
}