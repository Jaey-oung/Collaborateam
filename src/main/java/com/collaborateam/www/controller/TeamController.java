package com.collaborateam.www.controller;

import com.collaborateam.www.domain.TeamDto;
import com.collaborateam.www.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamService teamService;

    @GetMapping("/list")
    public String list(HttpServletRequest request) {
        if(!loginCheck(request))    // If not logged in
            return "redirect:/login/login?redirectUrl="+request.getRequestURL();    // Redirect to the login page

        return "teamList";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("mode", "CRT_TEAM");
        return "team";
    }

    @PostMapping("/create")
    public String create(TeamDto teamDto, Model model, RedirectAttributes rattr, HttpSession session) {
        String leader = (String)session.getAttribute("id");
        teamDto.setLeader(leader);

        try {
            int rowCnt = teamService.create(teamDto);

            if(rowCnt != 1)
                throw new Exception("Team create failed");

            rattr.addFlashAttribute("msg", "TEAM_CRT_OK");
            return "redirect:/team/list";
        } catch (Exception e) {
            model.addAttribute("teamDto", teamDto);
            model.addAttribute("msg", "TEAM_CRT_ERR");
            return "team";
        }
    }

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("id")!=null;    // If "id" exists in the session, return true
    }
}