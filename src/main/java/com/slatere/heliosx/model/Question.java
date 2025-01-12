package com.slatere.heliosx.model;

import com.slatere.heliosx.validation.ConsultationIdConstraint;
import com.slatere.heliosx.validation.QuestionConstraint;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

@QuestionConstraint
public class Question {

    private UUID id;

    // TODO would be good if consultation and question were separated.
    @NotNull
    @ConsultationIdConstraint
    private UUID consultationId;

    @NotNull
    private String text;

    // TODO would be much better if answers were separated out into more tables.
    private List<String> answers;

    private List<String> validAnswers;

    @NotNull
    private QuestionTypeEnum questionType;

    public Question(UUID id, UUID consultationId, String text, List<String> answers, List<String> validAnswers, QuestionTypeEnum questionType) {
        this.id = id;
        this.consultationId = consultationId;
        this.text = text;
        this.answers = answers;
        this.validAnswers = validAnswers;
        this.questionType = questionType;
    }

    public Question(UUID id, Question question) {
        this.id = id;
        this.consultationId = question.getConsultationId();
        this.text = question.getText();
        this.answers = question.getAnswers();
        this.validAnswers = question.getValidAnswers();
        this.questionType = question.getQuestionType();
    }

    public Question() {
    }

    public UUID getId() {
        return id;
    }

    public UUID getConsultationId() {
        return consultationId;
    }

    public String getText() {
        return text;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public List<String> getValidAnswers() {
        return validAnswers;
    }

    public QuestionTypeEnum getQuestionType() {
        return questionType;
    }
}
