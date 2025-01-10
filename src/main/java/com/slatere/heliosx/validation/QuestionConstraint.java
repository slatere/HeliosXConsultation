package com.slatere.heliosx.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = QuestionConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface QuestionConstraint {
    String message() default "The question is invalid.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
