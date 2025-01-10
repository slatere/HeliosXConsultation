package com.slatere.heliosx.repository;

import com.slatere.heliosx.model.Consultation;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ConsultationRepository {

    List<Consultation> consultationList;

    @PostConstruct
    public void setUp() {
        consultationList = new ArrayList<>();
    }


    public Consultation save(Consultation consultation) {
        consultationList.add(consultation);
        return consultation;
    }

    public void delete(Consultation consultation) {
        consultationList.remove(consultation);
    }

    public Optional<Consultation> findById(UUID id) {
        for (Consultation consultation: consultationList) {
            if(consultation.getId().equals(id)) {
                return Optional.of(consultation);
            }
        }
        return Optional.empty();
    }

    public List<Consultation> getAll() {
        return consultationList;
    }
}