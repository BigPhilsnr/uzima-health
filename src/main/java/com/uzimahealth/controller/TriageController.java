package com.uzimahealth.controller;

import com.uzimahealth.model.Triage;
import com.uzimahealth.service.TriageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/triage")
public class TriageController {
    @Autowired
    private TriageService triageService;

    @PostMapping
    public ResponseEntity<Triage> recordTriage(@RequestParam Long visitId, @RequestParam double systolic,
                                               @RequestParam double diastolic, @RequestParam int heartRate,
                                               @RequestParam double temperature, @RequestParam int respiratoryRate,
                                               @RequestParam double weight, @RequestParam double height,
                                               @RequestParam String category, @RequestParam String notes,
                                               @RequestParam Long userId) {
        Triage triage = triageService.recordTriage(visitId, systolic, diastolic, heartRate, temperature,
                                                   respiratoryRate, weight, height, category, notes, userId);
        return ResponseEntity.ok(triage);
    }

    @GetMapping("/visit/{visitId}")
    public ResponseEntity<Triage> getTriageByVisitId(@PathVariable Long visitId) {
        Optional<Triage> triage = triageService.getTriageByVisitId(visitId);
        return triage.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}