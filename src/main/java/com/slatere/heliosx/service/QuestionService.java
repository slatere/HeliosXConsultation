package com.slatere.heliosx.service;


import com.slatere.heliosx.model.Question;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuestionService {

    public Question save(Question question);

    public Optional<Question> findById(UUID questionId);

    public List<Question> findByConsultationId(UUID consultationId);

    public void delete(Question question);

    public List<Question> saveQuestions(List<Question> question);

    public List<Question> getAll();
}
