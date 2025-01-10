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

    @Override
    public boolean isValid(ConsultationAnswer consultationAnswer, ConstraintValidatorContext constraintValidatorContext) {
        Optional<Question> optionalQuestion = questionService.getQuestionById(consultationAnswer.getQuestionId());
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

        boolean unique = consultationAnswerService.checkCombinationOfConsultationAndQuestionIdUnique(consultationAnswer);
        if (unique) {
            return true;
        } else {
            constraintValidatorContext.buildConstraintViolationWithTemplate(String.format(
                    "The combination of consultationId %d and questionId are not unique",
                    consultationAnswer.getConsultationId(), consultationAnswer.getQuestionId())).addConstraintViolation();
        }
        return false;
    }
}
