package com.collaborateam.www.domain;

import org.springframework.validation.Errors;
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
        String name = userDto.getName();

        boolean isIdNullOrEmpty = id == null || id.trim().isEmpty();
        boolean isPwdNullOrEmpty = pwd == null || pwd.trim().isEmpty();
        boolean isNameNullOrEmpty = name == null || name.trim().isEmpty();

        if (isIdNullOrEmpty || isPwdNullOrEmpty || isNameNullOrEmpty) {
            if (isIdNullOrEmpty) {
                errors.rejectValue("id", "required");
            } else if (isPwdNullOrEmpty) {
                errors.rejectValue("pwd", "required");
            } else {
                errors.rejectValue("name", "required");
            }
        } else {
            if (id.length() < 8 || id.length() > 12) {
                errors.rejectValue("id", "invalidLength", new String[]{"8", "12"}, null);
            } else if (pwd.length() < 8 || pwd.length() > 12) {
                errors.rejectValue("pwd", "invalidLength", new String[]{"8", "12"}, null);
            }
        }
    }
}