package com.laau.tranquilnest.validator;

import com.laau.tranquilnest.enums.Role;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class RoleValidator implements ConstraintValidator<RoleConstraint, String> {
    @Override
    public void initialize(RoleConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Arrays.stream(Role.values()).anyMatch(
                role -> role.name().equalsIgnoreCase(value));
    }
}
