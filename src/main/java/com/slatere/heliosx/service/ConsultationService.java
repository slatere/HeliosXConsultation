package com.slatere.heliosx.service;


import com.slatere.heliosx.model.Consultation;
import com.slatere.heliosx.model.ConsultationAnswer;
import com.slatere.heliosx.model.Question;
import com.slatere.heliosx.response.UserPerscribeResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConsultationService {

    public Consultation create(Consultation consultation);

    public Optional<Consultation> findById(UUID consultationId);

    public void delete(Consultation consultation);

    public List<Consultation> createConsultations(List<Consultation> consultations);

    public List<Consultation> getAll();

    public UserPerscribeResponse areLikelyToPrescribe(List<ConsultationAnswer> consultationAnswers);
}
