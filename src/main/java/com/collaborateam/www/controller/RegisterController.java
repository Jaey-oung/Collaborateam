package com.collaborateam.www.controller;

import com.collaborateam.www.domain.UserDto;
import com.collaborateam.www.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

    @GetMapping("/add")
    public String registerForm() {
        return "registerForm";
    }

    @InitBinder
    public void toDate(WebDataBinder binder) {  // Text -> Date
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
    }

    @PostMapping("/add")
    public String save(@Valid UserDto userDto, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if(result.hasErrors()) {
            return "registerForm";
        }

        try {
            int rowCnt = userService.addUser(userDto);
            if(rowCnt != 1)
                throw new Exception("Sign Up Failed");
            redirectAttributes.addFlashAttribute("msg", "SIGNUP_OK");
            return "redirect:/";    // Redirect to the home page
        } catch (Exception e) {
            model.addAttribute("userDto", userDto);
            model.addAttribute("msg", "SIGNUP_ERR");
            return "registerForm";
        }
    }
}