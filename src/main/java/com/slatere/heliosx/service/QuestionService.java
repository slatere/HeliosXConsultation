package com.slatere.heliosx.service;


import com.slatere.heliosx.model.Question;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuestionService {

    public Question createQuestion(Question question);

    public Optional<Question> getQuestionById(UUID questionId);

    public List<Question> getByConsultationId(UUID consultationId);

    public void deleteQuestion(Question question);

    public List<Question> createQuestions(List<Question> question);

    public List<Question> getAll();
}
