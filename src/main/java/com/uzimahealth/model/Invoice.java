package com.uzimahealth.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String invoiceNumber;
    @OneToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;
    private LocalDateTime invoiceDate;
    private double consultationFee;
    private double labFee;
    private double radiologyFee;
    private double pharmacyFee;
    private double totalAmount;
    private String status; // Pending, Paid, Cancelled
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<Payment> payments;
    @ManyToOne
    @JoinColumn(name = "generated_by")
    private User generatedBy;

    public Invoice() {}

    public Invoice(String invoiceNumber, Visit visit, double consultationFee, double labFee,
                   double radiologyFee, double pharmacyFee, User generatedBy) {
        this.invoiceNumber = invoiceNumber;
        this.visit = visit;
        this.consultationFee = consultationFee;
        this.labFee = labFee;
        this.radiologyFee = radiologyFee;
        this.pharmacyFee = pharmacyFee;
        this.totalAmount = consultationFee + labFee + radiologyFee + pharmacyFee;
        this.generatedBy = generatedBy;
        this.invoiceDate = LocalDateTime.now();
        this.status = "Pending";
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
    public Visit getVisit() { return visit; }
    public void setVisit(Visit visit) { this.visit = visit; }
    public LocalDateTime getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(LocalDateTime invoiceDate) { this.invoiceDate = invoiceDate; }
    public double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(double consultationFee) { this.consultationFee = consultationFee; this.totalAmount = calculateTotal(); }
    public double getLabFee() { return labFee; }
    public void setLabFee(double labFee) { this.labFee = labFee; this.totalAmount = calculateTotal(); }
    public double getRadiologyFee() { return radiologyFee; }
    public void setRadiologyFee(double radiologyFee) { this.radiologyFee = radiologyFee; this.totalAmount = calculateTotal(); }
    public double getPharmacyFee() { return pharmacyFee; }
    public void setPharmacyFee(double pharmacyFee) { this.pharmacyFee = pharmacyFee; this.totalAmount = calculateTotal(); }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<Payment> getPayments() { return payments; }
    public void setPayments(List<Payment> payments) { this.payments = payments; }
    public User getGeneratedBy() { return generatedBy; }
    public void setGeneratedBy(User generatedBy) { this.generatedBy = generatedBy; }

    private double calculateTotal() {
        return consultationFee + labFee + radiologyFee + pharmacyFee;
    }
}