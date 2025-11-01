package com.uzimahealth.service;

import com.uzimahealth.model.Triage;
import com.uzimahealth.model.User;
import com.uzimahealth.model.Visit;
import com.uzimahealth.repository.TriageRepository;
import com.uzimahealth.repository.UserRepository;
import com.uzimahealth.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TriageService {
    @Autowired
    private TriageRepository triageRepository;
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private UserRepository userRepository;

    public Triage recordTriage(Long visitId, double systolic, double diastolic, int heartRate,
                               double temperature, int respiratoryRate, double weight, double height,
                               String category, String notes, Long userId) {
        Optional<Visit> visit = visitRepository.findById(visitId);
        Optional<User> user = userRepository.findById(userId);
        if (visit.isPresent() && user.isPresent()) {
            Triage triage = new Triage(visit.get(), systolic, diastolic, heartRate, temperature,
                                       respiratoryRate, weight, height, category, notes, user.get());
            return triageRepository.save(triage);
        }
        throw new RuntimeException("Visit or User not found");
    }

    public Optional<Triage> getTriageByVisitId(Long visitId) {
        return triageRepository.findAll().stream()
                .filter(t -> t.getVisit().getId().equals(visitId))
                .findFirst();
    }
}