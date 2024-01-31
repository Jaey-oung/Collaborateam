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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
            int totalCnt = boardService.getBoardCnt(blc);
            model.addAttribute("totalCnt", totalCnt);

            Pagination pagination = new Pagination(totalCnt, blc);
            List<BoardDto> list = boardService.getBoardPage(blc);

            model.addAttribute("list", list);
            model.addAttribute("pagination", pagination);

            Instant today = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            model.addAttribute("today", today.toEpochMilli());
        } catch (Exception e) {
            model.addAttribute("totalCnt", 0);
        }
        return "boardList";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("mode", "BOARD_CRT");
        return "board";
    }

    @PostMapping("/create")
    public String create(BoardDto boardDto, Model model, RedirectAttributes rattr, HttpSession session) {
        String writer = (String)session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            int rowCnt = boardService.create(boardDto);

            if(rowCnt != 1)
                throw new Exception("Board create failed");

            rattr.addFlashAttribute("msg", "BOARD_CRT_OK");
            return "redirect:/board/list";
        } catch (Exception e) {
            model.addAttribute("boardDto", boardDto);
            model.addAttribute("msg", "BOARD_CRT_ERR");
            return "board";
        }
    }

    @GetMapping("/read")
    public String read(Integer bno, BoardListCondition blc, Model model, RedirectAttributes rattr) {
        try {
            BoardDto boardDto = boardService.read(bno);

            if(boardDto == null)
                throw new Exception("Board read failed");

            model.addAttribute("boardDto", boardDto);
            model.addAttribute("mode", "BOARD_READ");
            return "board";
        } catch (Exception e) {
            rattr.addFlashAttribute("msg", "BOARD_READ_ERR");
            return "redirect:/board/list"+blc.getQueryString();
        }
    }

    @PostMapping("/update")
    public String update(BoardDto boardDto, Model model, RedirectAttributes rattr, HttpSession session) {
        String writer = (String)session.getAttribute("id");
        boardDto.setWriter(writer);

        try {
            int rowCnt = boardService.update(boardDto);

            if(rowCnt != 1)
                throw new Exception("Board update failed");

            rattr.addFlashAttribute("msg", "BOARD_UPD_OK");
            return "redirect:/board/list";
        } catch (Exception e) {
            model.addAttribute("boardDto", boardDto);
            model.addAttribute("msg", "BOARD_UPD_ERR");
            return "board";
        }
    }

    @PostMapping("/delete")
    public String delete(Integer bno, RedirectAttributes rattr, HttpSession session) {
        String writer = (String)session.getAttribute("id");

        try {
            int rowCnt = boardService.delete(bno, writer);

            if(rowCnt != 1)
                throw new Exception("Board delete failed");

            rattr.addFlashAttribute("msg", "BOARD_DEL_OK");
            return "redirect:/board/list";
        } catch (Exception e) {
            rattr.addFlashAttribute("msg", "BOARD_DEL_ERR");
            return "board";
        }
    }

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("id")!=null;    // If "id" exists in the session, return true
    }
}