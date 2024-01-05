package com.collaborateam.www.controller;

import com.collaborateam.www.domain.UserDto;
import com.collaborateam.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(String id, String pwd, RedirectAttributes redirectAttributes, String redirectUrl,
                        boolean rememberId, HttpServletRequest request, HttpServletResponse response) {
        if(!loginCheck(id, pwd)) {
            redirectAttributes.addFlashAttribute("msg", "LOGIN_ERR");
            return "redirect:/login/login?redirectUrl="+redirectUrl;
        }

        HttpSession session = request.getSession();
        session.setAttribute("id", id);

        Cookie cookie = new Cookie("id", id);
        if (!rememberId) {
            cookie.setMaxAge(0);
        }
        response.addCookie(cookie);

        redirectUrl = (redirectUrl == null || redirectUrl.isEmpty()) ? "/" : redirectUrl;

        return "redirect:"+redirectUrl;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    private boolean loginCheck(String id, String pwd) {
        UserDto userDto;

        try {
            userDto = userService.selectUser(id);
        } catch (Exception e) {
            return false;
        }
        return userDto!=null && userDto.getPwd().equals(pwd);
    }
}
