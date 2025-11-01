package com.uzimahealth.controller;

import com.uzimahealth.model.Consultation;
import com.uzimahealth.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {
    @Autowired
    private ConsultationService consultationService;

    @PostMapping
    public ResponseEntity<Consultation> recordConsultation(@RequestParam Long visitId, @RequestParam String symptoms,
                                                           @RequestParam String findings, @RequestParam String diagnosis,
                                                           @RequestParam String notes, @RequestParam String treatmentPlan,
                                                           @RequestParam Long userId) {
        Consultation consultation = consultationService.recordConsultation(visitId, symptoms, findings, diagnosis,
                                                                           notes, treatmentPlan, userId);
        return ResponseEntity.ok(consultation);
    }

    @GetMapping("/visit/{visitId}")
    public ResponseEntity<Consultation> getConsultationByVisitId(@PathVariable Long visitId) {
        Optional<Consultation> consultation = consultationService.getConsultationByVisitId(visitId);
        return consultation.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}