package com.slatere.heliosx.service;

import com.slatere.heliosx.model.Question;
import com.slatere.heliosx.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionServiceImpl implements QuestionService {

    QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question save(Question question) {
        return questionRepository.save(new Question(UUID.randomUUID(), question));
    }

    @Override
    public Optional<Question> findById(UUID questionId) {
        return questionRepository.findById(questionId);
    }

    @Override
    public List<Question> findByConsultationId(UUID consultationId) {
        return questionRepository.findByConsultationId(consultationId);
    }

    @Override
    public void delete(Question question) {
        questionRepository.delete(question);
    }

    @Override
    public List<Question> getAll() {
        return questionRepository.getAll();
    }

    @Override
    public List<Question> saveQuestions(List<Question> questionList) {
        List<Question> newQuestionList = new ArrayList<>();
        for (Question question: questionList) {
            newQuestionList.add(save(question));
        }
        return newQuestionList;
    }
}
