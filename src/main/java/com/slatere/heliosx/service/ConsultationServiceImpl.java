package com.slatere.heliosx.service;

import com.slatere.heliosx.exception.ConsultationNotFoundException;
import com.slatere.heliosx.exception.ConsultationServiceException;
import com.slatere.heliosx.model.Consultation;
import com.slatere.heliosx.model.ConsultationAnswer;
import com.slatere.heliosx.model.Question;
import com.slatere.heliosx.model.QuestionTypeEnum;
import com.slatere.heliosx.repository.ConsultationRepository;
import com.slatere.heliosx.repository.QuestionRepository;
import com.slatere.heliosx.response.UserPrescribeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ConsultationServiceImpl implements ConsultationService {

    ConsultationRepository consultationRepository;

    QuestionRepository questionRepository;

    @Autowired
    public ConsultationServiceImpl(ConsultationRepository consultationRepository, QuestionRepository questionRepository) {
        this.consultationRepository = consultationRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public Consultation save(Consultation consultation) {
        return consultationRepository.save(new Consultation(UUID.randomUUID(), consultation));
    }

    @Override
    public Consultation findById(UUID consultationId) {
        Optional<Consultation> optionalConsultation = consultationRepository.findById(consultationId);
        if (optionalConsultation.isPresent()) {
            return optionalConsultation.get();
        } else{
            throw new ConsultationNotFoundException("Could not find consultation with id: " +  consultationId);
        }
    }

    @Override
    public void delete(Consultation consultation) {
        consultationRepository.delete(consultation);
    }

    @Override
    public List<Consultation> saveConsultations(List<Consultation> consultations) {
        List<Consultation> newConsultations = new ArrayList<>();
        for (Consultation consultation: consultations) {
            newConsultations.add(save(consultation));
        }
        return newConsultations;
    }

    @Override
    public List<Consultation> getAll() {
        return consultationRepository.getAll();
    }

    @Override
    public UserPrescribeResponse areLikelyToPrescribe(List<ConsultationAnswer> consultationAnswers) {
        for (ConsultationAnswer consultationAnswer: consultationAnswers) {
            Optional<Question> optionalQuestion = questionRepository.findById(consultationAnswer.getQuestionId());
            if (optionalQuestion.isPresent()) {
                if(optionalQuestion.get().getQuestionType() != QuestionTypeEnum.STRING &&
                        !optionalQuestion.get().getValidAnswers().contains(consultationAnswer.getTextAnswer())) {
                    return new UserPrescribeResponse(consultationAnswers.get(0).getUserId(), false);
                }
            } else {
                throw new ConsultationServiceException(
                        String.format("Question with id: %s not found for ConsultationAnswer consultationId: %s, cannot determine whether to prescribe.",
                        consultationAnswer.getQuestionId(), consultationAnswer.getConsultationId()));
            }
        }
        return new UserPrescribeResponse(consultationAnswers.get(0).getUserId(), true);
    }
}
