package com.uzimahealth.service;

import com.uzimahealth.model.Consultation;
import com.uzimahealth.model.User;
import com.uzimahealth.model.Visit;
import com.uzimahealth.repository.ConsultationRepository;
import com.uzimahealth.repository.UserRepository;
import com.uzimahealth.repository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultationService {
    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private UserRepository userRepository;

    public Consultation recordConsultation(Long visitId, String symptoms, String findings, String diagnosis,
                                           String notes, String treatmentPlan, Long userId) {
        Optional<Visit> visit = visitRepository.findById(visitId);
        Optional<User> user = userRepository.findById(userId);
        if (visit.isPresent() && user.isPresent()) {
            Consultation consultation = new Consultation(visit.get(), symptoms, findings, diagnosis,
                                                        notes, treatmentPlan, user.get());
            return consultationRepository.save(consultation);
        }
        throw new RuntimeException("Visit or User not found");
    }

    public Optional<Consultation> getConsultationByVisitId(Long visitId) {
        return consultationRepository.findAll().stream()
                .filter(c -> c.getVisit().getId().equals(visitId))
                .findFirst();
    }
}