package com.collaborateam.www.controller;

import com.collaborateam.www.domain.Pagination;
import com.collaborateam.www.domain.TeamDto;
import com.collaborateam.www.domain.TeamListCondition;
import com.collaborateam.www.service.MemberService;
import com.collaborateam.www.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamService teamService;
    @Autowired
    MemberService memberService;

    @GetMapping("/list")
    public String list(TeamListCondition tlc, Model model, HttpServletRequest request, HttpSession session) {
        if(!loginCheck(request))    // If not logged in
            return "redirect:/login/login?redirectUrl="+request.getRequestURL();    // Redirect to the login page

        String id = (String)session.getAttribute("id");

        try {
            int rowCnt = teamService.getTeamCnt(id);

            if(rowCnt == 0)
                throw new Exception("Team list load failed");

            Pagination pagination = new Pagination(rowCnt, tlc);
            List<TeamDto> list = teamService.getTeamPage(id, tlc);

            model.addAttribute("list", list);
            model.addAttribute("pagination", pagination);
        } catch (Exception e) {
            model.addAttribute("msg", "TEAM_LIST_LOAD_ERR");
        }
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

    @GetMapping("/read")
    public String read(Integer tno, TeamListCondition tlc, Model model, RedirectAttributes rattr) {
        try {
            TeamDto teamDto = teamService.read(tno);

            if(teamDto == null)
                throw new Exception("Team load failed");

            model.addAttribute("teamDto", teamDto);
            model.addAttribute("mode", "READ_TEAM");
            return "team";
        } catch (Exception e) {
            rattr.addFlashAttribute("msg", "TEAM_LOAD_ERR");
            return "redirect:/team/list"+tlc.getQueryString();
        }
    }

    @PostMapping("/modify")
    public String modify(TeamDto teamDto, TeamListCondition tlc, Model model, RedirectAttributes rattr, HttpSession session) {
        String leader = (String)session.getAttribute("id");
        teamDto.setLeader(leader);

        try {
            int rowCnt = teamService.modify(teamDto);

            if(rowCnt != 1)
                throw new Exception("Team modify failed");

            rattr.addFlashAttribute("msg", "TEAM_MOD_OK");
            return "redirect:/team/list"+tlc.getQueryString();
        } catch (Exception e) {
            model.addAttribute("teamDto", teamDto);
            model.addAttribute("msg", "BOARD_MOD_ERR");
            return "team";
        }
    }

    @PostMapping("/remove")
    public String remove(Integer tno, TeamListCondition tlc, RedirectAttributes rattr, HttpSession session) {
        String leader = (String)session.getAttribute("id");

        try {
            int rowCnt = teamService.remove(tno, leader);

            if(rowCnt != 1)
                throw new Exception("Team delete failed");

            rattr.addFlashAttribute("msg", "TEAM_DEL_OK");
            return "redirect:/team/list"+tlc.getQueryString();
        } catch (Exception e) {
            rattr.addFlashAttribute("msg", "TEAM_DEL_ERR");
            return "teamList";
        }
    }

    @RequestMapping("/teams")
    @ResponseBody
    public ResponseEntity<List<TeamDto>> getMyTeams(String leader) {
        try {
            List<TeamDto> list = teamService.retrieveLeaderTeam(leader);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean loginCheck(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute("id")!=null;    // If "id" exists in the session, return true
    }
}