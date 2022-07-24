package com.spotifyplaylist.vallidation;

import com.spotifyplaylist.service.UserService;
import com.spotifyplaylist.vallidation.annotation.UniqueEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserService userService;

    public UniqueEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            return this.userService.getUserByEmail(value) == null;
        } catch (NullPointerException ex) {
            return true;
        }
    }
}