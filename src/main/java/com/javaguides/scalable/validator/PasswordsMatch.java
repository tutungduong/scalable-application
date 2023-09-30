package com.javaguides.scalable.validator;

import com.javaguides.scalable.validator.impl.PasswordsMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


import java.lang.annotation.*;

@Constraint(validatedBy = PasswordsMatchValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface PasswordsMatch {

    String message() default "{PasswordsNotMatching}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
