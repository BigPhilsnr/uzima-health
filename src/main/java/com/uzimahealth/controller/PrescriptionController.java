package com.uzimahealth.controller;

import com.uzimahealth.model.Prescription;
import com.uzimahealth.model.PrescriptionItem;
import com.uzimahealth.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping
    public ResponseEntity<Prescription> createPrescription(@RequestParam Long visitId,
                                                           @RequestBody List<PrescriptionItem> items,
                                                           @RequestParam Long userId) {
        Prescription prescription = prescriptionService.createPrescription(visitId, items, userId);
        return ResponseEntity.ok(prescription);
    }

    @PostMapping("/{id}/dispense")
    public ResponseEntity<Void> dispensePrescription(@PathVariable Long id) {
        prescriptionService.dispensePrescription(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/visit/{visitId}")
    public ResponseEntity<List<Prescription>> getPrescriptionsByVisitId(@PathVariable Long visitId) {
        return ResponseEntity.ok(prescriptionService.getPrescriptionsByVisitId(visitId));
    }
}