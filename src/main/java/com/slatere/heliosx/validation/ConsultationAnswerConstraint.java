package com.slatere.heliosx.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = ConsultationAnswerConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConsultationAnswerConstraint {
    String message() default "The consultation answer is was invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


