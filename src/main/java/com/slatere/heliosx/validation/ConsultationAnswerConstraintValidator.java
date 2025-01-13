package com.slatere.heliosx.validation;

import com.slatere.heliosx.model.ConsultationAnswer;
import com.slatere.heliosx.model.Question;
import com.slatere.heliosx.model.QuestionTypeEnum;
import com.slatere.heliosx.service.ConsultationAnswerService;
import com.slatere.heliosx.service.QuestionService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ConsultationAnswerConstraintValidator implements ConstraintValidator<ConsultationAnswerConstraint, ConsultationAnswer> {

    QuestionService questionService;

    ConsultationAnswerService consultationAnswerService;

    @Autowired
    public ConsultationAnswerConstraintValidator(QuestionService questionService, ConsultationAnswerService consultationAnswerService) {
        this.questionService = questionService;
        this.consultationAnswerService = consultationAnswerService;
    }

    // TODO - refactor out with the factory pattern.
    @Override
    public boolean isValid(ConsultationAnswer consultationAnswer, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        Optional<Question> optionalQuestion = questionService.findById(consultationAnswer.getQuestionId());
        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            if (question.getQuestionType() == QuestionTypeEnum.STRING ||
                    question.getAnswers().contains(consultationAnswer.getTextAnswer())) {
                return true;
            } else {
                constraintValidatorContext.buildConstraintViolationWithTemplate(String.format(
                        "The given answer %s is not apart of the Question: %s answer list.",
                        consultationAnswer.getTextAnswer(), consultationAnswer.getQuestionId())).addConstraintViolation();
            }
        }

        if (consultationAnswerService.checkCombinationOfConsultationAndQuestionIdUnique(consultationAnswer)) {
            return true;
        } else {
            constraintValidatorContext.buildConstraintViolationWithTemplate(String.format(
                    "The combination of consultationId %s and questionId %s are not unique",
                    consultationAnswer.getConsultationId(), consultationAnswer.getQuestionId())).addConstraintViolation();
        }
        return false;
    }
}
