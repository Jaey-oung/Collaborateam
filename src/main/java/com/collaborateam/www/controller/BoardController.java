package com.collaborateam.www.controller;

import com.collaborateam.www.domain.BoardDto;
import com.collaborateam.www.domain.Pagination;
import com.collaborateam.www.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping("/read")
    public String read(Integer bno, Integer page, Integer pageSize, Model model, RedirectAttributes redirectAttributes) {
        try {
            BoardDto boardDto = boardService.read(bno);

            if(boardDto == null)
                throw new Exception("Board load failed");

            model.addAttribute("boardDto", boardDto);
            model.addAttribute("page", page);
            model.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg", "BOARD_LOAD_ERR");
            return "redirect:/board/list";
        }
        return "board";
    }

    @GetMapping("/list")
    public String list(Integer page, Integer pageSize, Model model, HttpServletRequest request) {
        if(!loginCheck(request))    // If not logged in
            return "redirect:/login/login?redirectUrl="+request.getRequestURL();    // Redirect to the login page

        if(page==null) page = 1;
        if(pageSize==null) pageSize = 10;

        int offset = (page-1) * pageSize;

        try {
            int rowCnt = boardService.getCount();

            Pagination pagination = new Pagination(rowCnt, page, pageSize);
            List<BoardDto> list = boardService.getPage(offset, pageSize);
            model.addAttribute("list", list);
            model.addAttribute("pagination", pagination);
            model.addAttribute("page", page);
            model.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            model.addAttribute("msg", "LIST_LOAD_ERR");
        }
        return "boardList";
    }

    private boolean loginCheck(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        return session.getAttribute("id")!=null;    // If "id" exists in the session, return true
        return true;
    }
}