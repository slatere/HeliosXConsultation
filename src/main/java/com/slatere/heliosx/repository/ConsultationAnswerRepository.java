package com.slatere.heliosx.repository;

import com.slatere.heliosx.model.ConsultationAnswer;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ConsultationAnswerRepository {

    List<ConsultationAnswer> consultationAnswerList;

    @PostConstruct
    private void setUp() {
        consultationAnswerList = new ArrayList<>();
    }

    public ConsultationAnswer save(ConsultationAnswer consultationAnswer) {
        consultationAnswerList.add(consultationAnswer);
        return consultationAnswer;
    }

    public void delete(ConsultationAnswer consultationAnswer) {
        consultationAnswerList.remove(consultationAnswer);
    }

    public List<ConsultationAnswer> getByConsultationId(UUID consultationId) {
        List<ConsultationAnswer> consultationAnswers = new ArrayList<>();
        for (ConsultationAnswer consultationAnswer: consultationAnswerList) {
            if (consultationAnswer.getConsultationId().equals(consultationId)) {
                consultationAnswers.add(consultationAnswer);
            }
        }
        return consultationAnswers;
    }

    public List<ConsultationAnswer> getAll() {
        return consultationAnswerList;
    }
}
