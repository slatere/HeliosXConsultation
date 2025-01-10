package com.slatere.heliosx.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Constraint(validatedBy = ConsultationIdConstraintValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConsultationIdConstraint {
    String message() default "Could not find a consulation with the consultationId.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
