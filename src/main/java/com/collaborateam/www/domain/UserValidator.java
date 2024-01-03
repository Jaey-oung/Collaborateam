package com.collaborateam.www.domain;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

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

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id",  "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");

//        if (!errors.hasFieldErrors("id") && (id.length() < 8 || id.length() > 12)) {
//            errors.rejectValue("id", "invalidLength", new String[]{"8", "12"}, null);
//        } else if (!errors.hasFieldErrors("pwd") && (pwd.length() < 8 || pwd.length() > 12)) {
//            errors.rejectValue("pwd", "invalidLength", new String[]{"8", "12"}, null);
//        }
        if (!errors.hasFieldErrors("id") && !errors.hasFieldErrors("pwd")) {
            if ((id.length() < 8 || id.length() > 12)) {
                errors.rejectValue("id", "invalidLength", new String[]{"8", "12"}, null);
            }

            if ((pwd.length() < 8 || pwd.length() > 12)) {
                errors.rejectValue("pwd", "invalidLength", new String[]{"8", "12"}, null);
            }
        }
    }
}