package com.collaborateam.www.domain;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionCatcher {
    @ExceptionHandler(Exception.class)
    public String catcher(Exception ex, Model m) {
        m.addAttribute("ex", ex);
        return "error";
    }
}