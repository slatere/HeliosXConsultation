package com.slatere.heliosx.service;

import com.slatere.heliosx.exception.ConsultationServiceException;
import com.slatere.heliosx.model.Consultation;
import com.slatere.heliosx.model.ConsultationAnswer;
import com.slatere.heliosx.model.Question;
import com.slatere.heliosx.model.QuestionTypeEnum;
import com.slatere.heliosx.repository.ConsultationRepository;
import com.slatere.heliosx.repository.QuestionRepository;
import com.slatere.heliosx.response.UserPerscribeResponse;
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
    public Consultation create(Consultation consultation) {
        return consultationRepository.save(new Consultation(UUID.randomUUID(), consultation));
    }

    @Override
    public Optional<Consultation> findById(UUID consultationId) {
        return consultationRepository.findById(consultationId);
    }

    @Override
    public void delete(Consultation consultation) {
        consultationRepository.delete(consultation);
    }

    @Override
    public List<Consultation> createConsultations(List<Consultation> consultations) {
        List<Consultation> newConsultations = new ArrayList<>();
        for (Consultation consultation: consultations) {
            newConsultations.add(create(consultation));
        }
        return newConsultations;
    }

    @Override
    public List<Consultation> getAll() {
        return consultationRepository.getAll();
    }

    /**
     *
     * @param consultationAnswers
     * @return boolean
     * This assumes the List of ConsultationAnswer are for one consultation.
     */
    @Override
    public UserPerscribeResponse areLikelyToPrescribe(List<ConsultationAnswer> consultationAnswers) {
        for (ConsultationAnswer consultationAnswer: consultationAnswers) {
            Optional<Question> optionalQuestion = questionRepository.findById(consultationAnswer.getQuestionId());
            if (optionalQuestion.isPresent()) {
                if(optionalQuestion.get().getQuestionType() != QuestionTypeEnum.STRING &&
                        !optionalQuestion.get().getValidAnswers().contains(consultationAnswer.getTextAnswer())) {
                    return new UserPerscribeResponse(consultationAnswers.get(0).getUserId(), false);
                }
            } else {
                throw new ConsultationServiceException(
                        String.format("Question with id: %d not found for ConsultationAnswer consultationId: %d, cannot determine whether to prescribe.",
                        consultationAnswer.getQuestionId(), consultationAnswer.getConsultationId()));
            }
        }
        return new UserPerscribeResponse(consultationAnswers.get(0).getUserId(), true);
    }
}
