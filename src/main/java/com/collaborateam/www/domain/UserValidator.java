package com.collaborateam.www.domain;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = (UserDto)target;

        String id = userDto.getId();
        String pwd = userDto.getPwd();

        Pattern idPattern = Pattern.compile("^[a-zA-Z0-9]+$");
        Pattern pwdPattern = Pattern.compile("(?=.*[0-9])(?=.*[!@#$%^&*()])(?=.*[a-zA-Z])");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
        if (errors.hasErrors()) return;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");
        if (errors.hasErrors()) return;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
        if (errors.hasErrors()) return;

        if(id.length() < 8 || id.length() > 12) {
            errors.rejectValue("id", "invalidLength", new String[]{"8", "12"}, null);
            return;
        }

        if(pwd.length() < 8 || pwd.length() > 12) {
            errors.rejectValue("pwd", "invalidLength", new String[]{"8", "12"}, null);
            return;
        }

        if(!idPattern.matcher(id).find()) {
            errors.rejectValue("id", "pattern");
            return;
        }

        if(!pwdPattern.matcher(pwd).find()) {
            errors.rejectValue("pwd", "pattern");
        }
    }
}