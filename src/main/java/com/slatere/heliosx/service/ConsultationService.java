package com.slatere.heliosx.service;


import com.slatere.heliosx.model.Consultation;
import com.slatere.heliosx.model.ConsultationAnswer;
import com.slatere.heliosx.response.UserPrescribeResponse;

import java.util.List;
import java.util.UUID;

public interface ConsultationService {

    public Consultation save(Consultation consultation);

    public Consultation findById(UUID consultationId);

    public void delete(Consultation consultation);

    public List<Consultation> saveConsultations(List<Consultation> consultations);

    public List<Consultation> getAll();

    public UserPrescribeResponse areLikelyToPrescribe(List<ConsultationAnswer> consultationAnswers);
}
