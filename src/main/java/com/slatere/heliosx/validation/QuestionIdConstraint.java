package com.slatere.heliosx.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = QuestionIdConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface QuestionIdConstraint {
    String message() default "Could not find a question with the questionId.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
