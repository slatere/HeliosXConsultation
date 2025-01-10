package com.slatere.heliosx.controller;

import com.slatere.heliosx.model.Consultation;
import com.slatere.heliosx.model.ConsultationAnswer;
import com.slatere.heliosx.model.Question;
import com.slatere.heliosx.response.UserPerscribeResponse;
import com.slatere.heliosx.service.ConsultationAnswerService;
import com.slatere.heliosx.service.ConsultationService;
import com.slatere.heliosx.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v1")
public class ConsultationController {

    ConsultationService consultationService;

    ConsultationAnswerService consultationAnswerService;

    QuestionService questionService;

    @Autowired
    public ConsultationController(ConsultationService consultationService, ConsultationAnswerService consultationAnswerService, QuestionService questionService) {
        this.consultationService = consultationService;
        this.consultationAnswerService = consultationAnswerService;
        this.questionService = questionService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/consultations")
    public List<Consultation> createConsultation(@RequestBody List<@Valid Consultation> consultations) {
        return consultationService.createConsultations(consultations);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/consultations")
    public List<Consultation> getConsultations() {
        return consultationService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/consultation/consultation-answers")
    public List<ConsultationAnswer> getConsultationAnswers() {
        return consultationAnswerService.getAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/consultation/consultation-answers")
    public UserPerscribeResponse createAnswersToConsultation(@RequestBody List<@Valid ConsultationAnswer> consultationAnswers) {
        List<ConsultationAnswer> answers = consultationAnswerService.createConsultationAnswers(consultationAnswers);
        return consultationService.areLikelyToPrescribe(answers);
    }

    //TODO Currently you can add same question with same text twice for the same consultation.
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/consultation/questions")
    public List<Question> createQuestions(@RequestBody List<@Valid Question> questions) {
        return questionService.createQuestions(questions);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/consultation/questions")
    public List<Question> getQuestions() {
        return questionService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/consultation/{consultation_id}/questions")
    public List<Question> getQuestionsByConsultationId(@PathVariable UUID consultation_id) {
        return questionService.getByConsultationId(consultation_id);
    }
}
