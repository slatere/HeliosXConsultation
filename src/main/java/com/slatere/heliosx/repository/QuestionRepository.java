package com.slatere.heliosx.repository;

import com.slatere.heliosx.model.Question;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class QuestionRepository {

    List<Question> questionList;

    @PostConstruct
    public void setUp() {
        questionList = new ArrayList<>();
    }

    public Question save(Question question) {
        questionList.add(question);
        return question;
    }

    public void delete(Question question) {
        questionList.remove(question);
    }

    public List<Question> getAll() {
        return questionList;
    }

    public Optional<Question> findById(UUID id) {
        for (Question question: questionList) {
            if (question.getId().equals(id)) {
                return Optional.of(question);
            }
        }
        return Optional.empty();
    }

    public List<Question> findByConsultationId(UUID consultationId) {
        List<Question> questionByConsultationId = new ArrayList<>();
        for (Question question: questionList) {
            if (question.getConsultationId().equals(consultationId)) {
                questionByConsultationId.add(question);
            }
        }
        return questionByConsultationId;
    }
}
