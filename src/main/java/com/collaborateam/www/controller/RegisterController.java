package com.collaborateam.www.controller;

import com.collaborateam.www.domain.UserDto;
import com.collaborateam.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @InitBinder
    public void toDate(WebDataBinder binder) {  // Text -> Date
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
    }

    @GetMapping("/create")
    public String create() {
        return "registerForm";
    }

    @PostMapping("/create")
    public String create(@Valid UserDto userDto, BindingResult result, RedirectAttributes rattr, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("err", result.hasErrors());
            return "registerForm";
        }

        try {
            String encodedPassword = passwordEncoder.encode(userDto.getPwd());
            userDto.setPwd(encodedPassword);

            int rowCnt = userService.create(userDto);

            if(rowCnt != 1)
                throw new Exception("User create failed");

            rattr.addFlashAttribute("msg", "USER_CRT_OK");
            return "redirect:/";    // Redirect to the home page
        } catch (Exception e) {
            model.addAttribute("userDto", userDto);
            model.addAttribute("msg", "USER_CRT_ERR");
            return "registerForm";
        }
    }
}