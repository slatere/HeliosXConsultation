package com.slatere.heliosx.model;

import com.slatere.heliosx.validation.ConsultationAnswerConstraint;
import com.slatere.heliosx.validation.ConsultationIdConstraint;
import com.slatere.heliosx.validation.QuestionIdConstraint;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@ConsultationAnswerConstraint
public class ConsultationAnswer {

    @NotNull
    @ConsultationIdConstraint
    private UUID consultationId;

    @NotNull
    @QuestionIdConstraint
    private UUID questionId;

    private UUID userId;

    @NotNull
    private String textAnswer;

    public ConsultationAnswer(UUID consultationId, UUID questionId, UUID userId, String textAnswer) {
        this.consultationId = consultationId;
        this.questionId = questionId;
        this.userId = userId;
        this.textAnswer = textAnswer;
    }

    public ConsultationAnswer(UUID userId, ConsultationAnswer consultationAnswer) {
        this.consultationId = consultationAnswer.getConsultationId();
        this.questionId = consultationAnswer.getQuestionId();
        this.userId = userId;
        this.textAnswer = consultationAnswer.getTextAnswer();
    }

    public ConsultationAnswer() {
    }

    public UUID getConsultationId() {
        return consultationId;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getTextAnswer() {
        return textAnswer;
    }
}
