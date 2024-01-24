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
    public String login(String id, String pwd, boolean rememberId, String redirectUrl,
                        RedirectAttributes rattr, HttpServletRequest request, HttpServletResponse response) {

        // Check if id and pwd are null or empty
        boolean isFieldNullOrEmpty = id == null || id.trim().isEmpty() || pwd == null || pwd.trim().isEmpty();

        if(isFieldNullOrEmpty || !loginCheck(id, pwd)) {
            rattr.addFlashAttribute("msg", isFieldNullOrEmpty ? "EMPTY_FIELD" : "LOGIN_ERR");
            rattr.addFlashAttribute("id", id);
            rattr.addFlashAttribute("pwd", pwd);
            return "redirect:/login/login?redirectUrl="+redirectUrl;    // Redirect to the login page
        }

        HttpSession session = request.getSession();
        session.setAttribute("id", id); // Set "id" in the session

        Cookie cookie = new Cookie("id", id);
        if (!rememberId) {
            cookie.setMaxAge(0);    // Expires cookie
        }
        response.addCookie(cookie);

        redirectUrl = (redirectUrl == null || redirectUrl.isEmpty()) ? "/" : redirectUrl;

        return "redirect:"+redirectUrl; // Redirect to the specified URL
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";    // Redirect to the home page
    }

    private boolean loginCheck(String id, String pwd) {
        UserDto userDto;
        try {
            userDto = userService.read(id);
        } catch (Exception e) {
            return false;
        }
        return userDto!=null && userDto.getPwd().equals(pwd);
    }
}