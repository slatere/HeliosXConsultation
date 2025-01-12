package com.slatere.heliosx.validation;

import com.slatere.heliosx.exception.ConsultationNotFoundException;
import com.slatere.heliosx.service.ConsultationService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class ConsultationIdConstraintValidator implements ConstraintValidator<ConsultationIdConstraint, UUID> {

    ConsultationService consultationService;

    @Autowired
    public ConsultationIdConstraintValidator(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @Override
    public boolean isValid(UUID uuid, ConstraintValidatorContext constraintValidatorContext) {
        try {
            consultationService.findById(uuid);
            return true;
        } catch (ConsultationNotFoundException ex) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(ex.getMessage()).addConstraintViolation();
            return false;
        }
    }
}
