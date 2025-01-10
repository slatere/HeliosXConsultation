package com.slatere.heliosx.service;

import com.slatere.heliosx.model.ConsultationAnswer;

import java.util.List;
import java.util.UUID;

public interface ConsultationAnswerService {

    public ConsultationAnswer save(ConsultationAnswer consultationAnswer);

    public void delete(ConsultationAnswer consultationAnswer);

    public List<ConsultationAnswer> getByConsultationId(UUID consultationId);

    public List<ConsultationAnswer> createConsultationAnswers(List<ConsultationAnswer> consultationAnswers);

    public List<ConsultationAnswer> getAll();

    public boolean checkCombinationOfConsultationAndQuestionIdUnique(ConsultationAnswer consultationAnswer);
}
