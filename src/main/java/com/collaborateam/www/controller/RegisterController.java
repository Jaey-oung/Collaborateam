package com.collaborateam.www.controller;

import com.collaborateam.www.dao.UserDao;
import com.collaborateam.www.domain.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    UserDao userDao;

    @GetMapping("/add")
    public String registerForm() {
        return "registerForm";
    }

    @InitBinder
    public void toDate(WebDataBinder binder) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));
    }

    @PostMapping("/add")
    public String registerResult(@Valid UserDto userDto, BindingResult result) throws Exception {
        System.out.println("result="+result);
        System.out.println("user="+userDto);

        if(result.hasErrors()) {
            return "registerForm";
        }

        userDao.insertUser(userDto);
        return "registerInfo";
    }
}
