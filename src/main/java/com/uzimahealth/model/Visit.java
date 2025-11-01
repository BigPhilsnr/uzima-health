package com.uzimahealth.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String visitNumber;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    private LocalDateTime checkInTime;
    private String status; // Active, Completed, Cancelled
    private String department; // General, Pediatrics, etc.

    public Visit() {}

    public Visit(String visitNumber, Patient patient, LocalDateTime checkInTime, String status, String department) {
        this.visitNumber = visitNumber;
        this.patient = patient;
        this.checkInTime = checkInTime;
        this.status = status;
        this.department = department;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getVisitNumber() { return visitNumber; }
    public void setVisitNumber(String visitNumber) { this.visitNumber = visitNumber; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public LocalDateTime getCheckInTime() { return checkInTime; }
    public void setCheckInTime(LocalDateTime checkInTime) { this.checkInTime = checkInTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}