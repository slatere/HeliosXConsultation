package com.slatere.heliosx.service;

import com.slatere.heliosx.model.ConsultationAnswer;
import com.slatere.heliosx.repository.ConsultationAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ConsultationAnswerServiceImpl implements ConsultationAnswerService {

    ConsultationAnswerRepository consultationAnswerRepository;

    @Autowired
    public ConsultationAnswerServiceImpl(ConsultationAnswerRepository consultationAnswerRepository) {
        this.consultationAnswerRepository = consultationAnswerRepository;
    }

    @Override
    public ConsultationAnswer save(ConsultationAnswer consultationAnswer) {
        return consultationAnswerRepository.save(consultationAnswer);
    }

    @Override
    public void delete(ConsultationAnswer consultationAnswer) {
        consultationAnswerRepository.delete(consultationAnswer);
    }

    @Override
    public List<ConsultationAnswer> getByConsultationId(UUID consultationId) {
        return consultationAnswerRepository.getByConsultationId(consultationId);
    }

    @Override
    public List<ConsultationAnswer> saveConsultationAnswers(List<ConsultationAnswer> consultationAnswers) {
        UUID userId = UUID.randomUUID();
        List<ConsultationAnswer> newConsultationAnswers = new ArrayList<>();
        for (ConsultationAnswer consultationAnswer: consultationAnswers) {
            ConsultationAnswer newConsultationAnswer = new ConsultationAnswer(userId, consultationAnswer);
            newConsultationAnswers.add(save(newConsultationAnswer));
        }
        return newConsultationAnswers;
    }

    @Override
    public List<ConsultationAnswer> getAll() {
        return consultationAnswerRepository.getAll();
    }

    @Override
    public boolean checkCombinationOfConsultationAndQuestionIdUnique(ConsultationAnswer consultationAnswer) {
        List<ConsultationAnswer> consultationAnswers = getAll();
        for (ConsultationAnswer repoConsultationAnswer: consultationAnswers) {
            if (consultationAnswer.getConsultationId().equals(repoConsultationAnswer.getConsultationId()) &&
                consultationAnswer.getQuestionId().equals(repoConsultationAnswer.getQuestionId())) {
                return false;
            }
        }
        return true;
    }
}
