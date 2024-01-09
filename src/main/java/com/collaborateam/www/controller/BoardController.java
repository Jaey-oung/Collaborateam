package com.collaborateam.www.controller;

import com.collaborateam.www.domain.BoardDto;
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

    @PostMapping("/remove")
    public String remove(Integer bno, Integer page, Integer pageSize, RedirectAttributes redirectAttributes, HttpSession session) {
        String writer = (String)session.getAttribute("id");

        try {
            int rowCnt = boardService.remove(bno, writer);

            if(rowCnt != 1)
                throw new Exception("Board remove error");

            redirectAttributes.addFlashAttribute("msg", "BOARD_DEL_OK");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "BOARD_DEL_ERR");
        }
        redirectAttributes.addFlashAttribute("page", page);
        redirectAttributes.addFlashAttribute("pageSize", pageSize);
        return "redirect:/board/list";
    }

    @GetMapping("/read")
    public String read(Integer bno, Integer page, Integer pageSize, Model model, RedirectAttributes redirectAttributes) {
        try {
            BoardDto boardDto = boardService.read(bno);

            if(boardDto == null)
                throw new Exception("Board load error");

            model.addAttribute("boardDto", boardDto);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "BOARD_LOAD_ERR");
            return "redirect:/board/list";
        }
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        return "board";
    }

    @GetMapping("/list")
    public String list(Integer page, Integer pageSize, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if(!loginCheck(request))    // If not logged in
            return "redirect:/login/login?redirectUrl="+request.getRequestURL();    // Redirect to the login page

        if(page==null) page = 1;
        if(pageSize==null) pageSize = 10;

        int offset = (page-1) * pageSize;

        try {
            int boardCnt = boardService.getCount();

            if(boardCnt < 0)
                throw new Exception("Board list load error");

            Pagination pagination = new Pagination(boardCnt, page, pageSize);
            List<BoardDto> list = boardService.getPage(offset, pageSize);

            model.addAttribute("list", list);
            model.addAttribute("pagination", pagination);
            model.addAttribute("page", page);
            model.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "BOARD_LIST_LOAD_ERR");
            return "redirect:/";
        }
        return "boardList";
    }

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("id")!=null;    // If "id" exists in the session, return true
    }
}