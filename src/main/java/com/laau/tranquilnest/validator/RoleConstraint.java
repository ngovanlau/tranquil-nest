package com.laau.tranquilnest.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {RoleValidator.class})
public @interface RoleConstraint {
    String message() default "Role invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
