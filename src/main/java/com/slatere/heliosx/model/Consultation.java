package com.slatere.heliosx.model;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class Consultation {

    private UUID id;

    // TODO this should be a foreign key into a table
    @NotNull(message = "Must have a medicationId")
    private UUID medicationId;

    @NotNull(message = "Must have title")
    private String title;

    @NotNull(message = "Must have description")
    private String description;

    public Consultation(UUID id, UUID medicationId, String title, String description) {
        this.id = id;
        this.medicationId = medicationId;
        this.title = title;
        this.description = description;
    }

    public Consultation(UUID id, Consultation consultation) {
        this.id = id;
        this.medicationId = consultation.getMedicationId();
        this.title = consultation.getTitle();
        this.description = consultation.getDescription();
    }

    public Consultation() {
    }


    public UUID getId() {
        return id;
    }

    public UUID getMedicationId() {
        return medicationId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
