package com.spotifyplaylist.user.vallidation;

import com.spotifyplaylist.user.service.UserService;
import com.spotifyplaylist.user.vallidation.annotation.UniqueUsername;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserService userService;

    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            return this.userService.getUserByUsername(value) == null;
        } catch (NullPointerException ex) {
            return true;
        }
    }
}