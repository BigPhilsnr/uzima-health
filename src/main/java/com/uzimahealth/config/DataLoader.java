package com.uzimahealth.config;

import com.uzimahealth.model.*;
import com.uzimahealth.repository.*;
import com.uzimahealth.stock.Item;
import com.uzimahealth.stock.Stock;
import com.uzimahealth.stock.StockLedgerEntry;
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
    private final ItemRepository itemRepository;
    private final StockRepository stockRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionItemRepository prescriptionItemRepository;
    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;

    public DataLoader(UserRepository userRepository, PatientRepository patientRepository,
                      VisitRepository visitRepository, TriageRepository triageRepository,
                      ConsultationRepository consultationRepository, ItemRepository itemRepository,
                      StockRepository stockRepository, PrescriptionRepository prescriptionRepository,
                      PrescriptionItemRepository prescriptionItemRepository, InvoiceRepository invoiceRepository,
                      PaymentRepository paymentRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.visitRepository = visitRepository;
        this.triageRepository = triageRepository;
        this.consultationRepository = consultationRepository;
        this.itemRepository = itemRepository;
        this.stockRepository = stockRepository;
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

        // Sample items
        Item paracetamol = itemRepository.save(new Item("ITM001", "Paracetamol 500mg", "Drug", "Tablets", "PharmaCorp", 50, 0.5, "Pain reliever"));
        Item amoxicillin = itemRepository.save(new Item("ITM002", "Amoxicillin 500mg", "Drug", "Capsules", "MediLab", 20, 1.2, "Antibiotic"));

        // Sample stock
        stockRepository.save(new Stock(paracetamol, "BATCH001", LocalDate.of(2026, 12, 31), 100, "Pharmacy Store", 0.5));
        stockRepository.save(new Stock(amoxicillin, "BATCH002", LocalDate.of(2026, 6, 30), 50, "Pharmacy Store", 1.2));

        // Sample prescription
        Prescription prescription = prescriptionRepository.save(new Prescription(visit1, Arrays.asList(), doctor));
        PrescriptionItem item1 = prescriptionItemRepository.save(new PrescriptionItem(prescription, paracetamol, 10, "1 tablet", "3 times daily", "5 days", "After meals"));
        prescription.setItems(Arrays.asList(item1));
        prescriptionRepository.save(prescription);

        // Sample invoice
        Invoice invoice = invoiceRepository.save(new Invoice("INV001", visit1, 50.0, 0.0, 0.0, 5.0, clerk));

        // Sample payment
        paymentRepository.save(new Payment(invoice, 55.0, "Cash", "CASH001", clerk, "Full payment"));
    }
}