package com.uzimahealth.service;

import com.uzimahealth.model.*;
import com.uzimahealth.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private PrescriptionItemRepository prescriptionItemRepository;
    @Autowired
    private VisitRepository visitRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private StockService stockService;

    public Prescription createPrescription(Long visitId, List<PrescriptionItem> items, Long userId) {
        Optional<Visit> visit = visitRepository.findById(visitId);
        Optional<User> user = userRepository.findById(userId);
        if (visit.isPresent() && user.isPresent()) {
            Prescription prescription = new Prescription(visit.get(), items, user.get());
            prescription = prescriptionRepository.save(prescription);
            for (PrescriptionItem item : items) {
                item.setPrescription(prescription);
                prescriptionItemRepository.save(item);
            }
            return prescription;
        }
        throw new RuntimeException("Visit or User not found");
    }

    public void dispensePrescription(Long prescriptionId) {
        Optional<Prescription> prescription = prescriptionRepository.findById(prescriptionId);
        if (prescription.isPresent()) {
            for (PrescriptionItem item : prescription.get().getItems()) {
                stockService.deductStock(item.getItem().getId(), item.getQuantity(), "Pharmacy Store",
                                        "Prescription " + prescriptionId, prescription.get().getPrescribedBy().getId());
            }
            prescription.get().setStatus("Dispensed");
            prescriptionRepository.save(prescription.get());
        }
    }

    public List<Prescription> getPrescriptionsByVisitId(Long visitId) {
        return prescriptionRepository.findAll().stream()
                .filter(p -> p.getVisit().getId().equals(visitId))
                .toList();
    }
}