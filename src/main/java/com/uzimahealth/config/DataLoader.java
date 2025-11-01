package com.uzimahealth.config;

import com.uzimahealth.model.*;
import com.uzimahealth.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final VisitRepository visitRepository;
    private final TriageRepository triageRepository;
    private final ConsultationRepository consultationRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionItemRepository prescriptionItemRepository;
    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;

    public DataLoader(UserRepository userRepository, PatientRepository patientRepository,
                      VisitRepository visitRepository, TriageRepository triageRepository,
                      ConsultationRepository consultationRepository, PrescriptionRepository prescriptionRepository,
                      PrescriptionItemRepository prescriptionItemRepository, InvoiceRepository invoiceRepository,
                      PaymentRepository paymentRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
        this.triageRepository = triageRepository;
        this.consultationRepository = consultationRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.prescriptionItemRepository = prescriptionItemRepository;
        this.invoiceRepository = invoiceRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Sample users with roles
        User admin = userRepository.save(new User("admin", "password", "System Administrator"));
        User doctor = userRepository.save(new User("doctor", "password", "Doctor"));
        User nurse = userRepository.save(new User("nurse", "password", "Triage Nurse"));
        User pharmacist = userRepository.save(new User("pharmacist", "password", "Pharmacist"));
        User clerk = userRepository.save(new User("clerk", "password", "Registration Clerk"));

        // Sample patients
        Patient patient1 = patientRepository.save(new Patient("John Doe", LocalDate.of(1990, 1, 1), "Male", "12345678",
                "0712345678", "NHIF123", "Jubilee Insurance", "Cash", "No known allergies", "john@example.com"));
        Patient patient2 = patientRepository.save(new Patient("Jane Smith", LocalDate.of(1995, 5, 15), "Female", "87654321",
                "0798765432", "NHIF456", null, "Insurance", "Asthma", "jane@example.com"));

        // Sample visits
        Visit visit1 = visitRepository.save(new Visit("V001", patient1, LocalDateTime.now(), "Active", "General"));
        Visit visit2 = visitRepository.save(new Visit("V002", patient2, LocalDateTime.now().minusHours(1), "Completed", "General"));

        // Sample triage
        triageRepository.save(new Triage(visit1, 120, 80, 72, 36.5, 16, 70, 1.75, "Routine", "Normal vitals", nurse));

        // Sample consultation
        consultationRepository.save(new Consultation(visit1, "Fever and cough", "Mild infection", "J00 - Acute nasopharyngitis",
                "Prescribed antibiotics", "Rest and medication", doctor));

        // Sample prescription (without items - items will be managed by stock service)
        Prescription prescription = prescriptionRepository.save(new Prescription(visit1, Arrays.asList(), doctor));
        // Note: Prescription items will be created when communicating with stock service
        prescriptionRepository.save(prescription);

        // Sample invoice
        Invoice invoice = invoiceRepository.save(new Invoice("INV001", visit1, 50.0, 0.0, 0.0, 5.0, clerk));

        // Sample payment
        paymentRepository.save(new Payment(invoice, 55.0, "Cash", "CASH001", clerk, "Full payment"));

        System.out.println("HMIS sample data loaded successfully!");
    }
}