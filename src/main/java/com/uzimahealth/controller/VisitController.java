package com.uzimahealth.controller;

import com.uzimahealth.model.Visit;
import com.uzimahealth.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/visits")
public class VisitController {
    @Autowired
    private VisitService visitService;

    @PostMapping
    public ResponseEntity<Visit> createVisit(@RequestParam Long patientId, @RequestParam String department) {
        Visit visit = visitService.createVisit(patientId, department);
        return ResponseEntity.ok(visit);
    }

    @GetMapping
    public ResponseEntity<List<Visit>> getAllVisits() {
        return ResponseEntity.ok(visitService.getAllVisits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visit> getVisitById(@PathVariable Long id) {
        Optional<Visit> visit = visitService.getVisitById(id);
        return visit.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Visit> updateVisitStatus(@PathVariable Long id, @RequestParam String status) {
        Visit visit = visitService.updateVisitStatus(id, status);
        return ResponseEntity.ok(visit);
    }
}