package com.spotifyplaylist.user.vallidation.annotation;

import com.spotifyplaylist.user.vallidation.UniqueUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {
    String message() default "Username already exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
