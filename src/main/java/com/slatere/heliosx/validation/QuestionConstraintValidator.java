package com.slatere.heliosx.validation;

import com.slatere.heliosx.model.Question;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

import static com.slatere.heliosx.model.QuestionTypeEnum.STRING;

public class QuestionConstraintValidator implements ConstraintValidator<QuestionConstraint, Question> {

    public void createErrorMessage(ConstraintValidatorContext constraintValidatorContext, Question question, String additionalMessage) {
        constraintValidatorContext.buildConstraintViolationWithTemplate(String.format(
                "Question invalid: %s. %s\n",
                question.getId(), additionalMessage)).addConstraintViolation();
    }

    public boolean checkQuestionTypeAndAnswer(Question question, ConstraintValidatorContext constraintValidatorContext) {
        List<String> answers = question.getAnswers();
        List<String> validAnswers = question.getValidAnswers();
         switch(question.getQuestionType()) {
            case STRING:
                if (question.getAnswers() != null || question.getValidAnswers() != null) {
                    createErrorMessage(constraintValidatorContext, question,
                            String.format("The answers and validAnswers must be null. Answer: %s and %s\n",
                            answers,
                            validAnswers));
                    return false;
                } else {
                    return true;
                }
            case MULTICHOICE:
                if (question.getAnswers() == null || question.getValidAnswers() == null) {
                    createErrorMessage(constraintValidatorContext, question,
                            String.format("The answers and validAnswers must not be null. Answer: %s and %s\n",
                                    answers,
                                    validAnswers));
                    return false;
                } else {
                    return true;
                }
            default:
                createErrorMessage(constraintValidatorContext, question,
                        String.format("The QuestionEnumType is incorrect %", question.getQuestionType()));
                return false;
        }
    }

    public boolean checkValidAnswer(Question question, ConstraintValidatorContext constraintValidatorContext) {
        if (question.getQuestionType() != STRING) {
            for (String validAnswer : question.getValidAnswers()) {
                if (!question.getAnswers().contains(validAnswer)) {
                    createErrorMessage(constraintValidatorContext, question,
                            String.format("Valid answers: %s not in answer", validAnswer));
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isValid(Question question, ConstraintValidatorContext constraintValidatorContext) {
        if (checkQuestionTypeAndAnswer(question, constraintValidatorContext)) {
            return checkValidAnswer(question, constraintValidatorContext);
        }
        return false;
    }
}
