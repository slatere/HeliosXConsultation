package com.slatere.heliosx.validation;

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
        boolean isValid = consultationService.findById(uuid).isPresent();
        if (!isValid) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(String.format(
                    String.format("Consultation id: %s invalid", uuid))).addConstraintViolation();
        }
        return isValid;
    }
}
