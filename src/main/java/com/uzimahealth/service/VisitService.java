package com.uzimahealth.service;

import com.uzimahealth.model.Patient;
import com.uzimahealth.model.Visit;
import com.uzimahealth.repository.PatientRepository;
import com.uzimahealth.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VisitService {
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private PatientRepository patientRepository;

    public Visit createVisit(Long patientId, String department) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (patient.isPresent()) {
            String visitNumber = "V" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            Visit visit = new Visit(visitNumber, patient.get(), LocalDateTime.now(), "Active", department);
            return visitRepository.save(visit);
        }
        throw new RuntimeException("Patient not found");
    }

    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    public Optional<Visit> getVisitById(Long id) {
        return visitRepository.findById(id);
    }

    public Visit updateVisitStatus(Long id, String status) {
        Optional<Visit> visit = visitRepository.findById(id);
        if (visit.isPresent()) {
            visit.get().setStatus(status);
            return visitRepository.save(visit.get());
        }
        throw new RuntimeException("Visit not found");
    }
}