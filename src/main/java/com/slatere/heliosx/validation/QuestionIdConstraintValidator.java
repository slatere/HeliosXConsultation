package com.slatere.heliosx.validation;

import com.slatere.heliosx.service.ConsultationService;
import com.slatere.heliosx.service.QuestionService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class QuestionIdConstraintValidator implements ConstraintValidator<QuestionIdConstraint, UUID> {

    QuestionService questionService;

    @Autowired
    public QuestionIdConstraintValidator(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public boolean isValid(UUID uuid, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = questionService.getQuestionById(uuid).isPresent();
        if (!isValid) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(String.format(
                    String.format("questionId: %s invalid", uuid))).addConstraintViolation();
        }
        return isValid;
    }
}
