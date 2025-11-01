package com.uzimahealth.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;
    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<PrescriptionItem> items;
    private LocalDateTime prescribedAt;
    @ManyToOne
    @JoinColumn(name = "prescribed_by")
    private User prescribedBy;
    private String status; // Pending, Dispensed

    public Prescription() {}

    public Prescription(Visit visit, List<PrescriptionItem> items, User prescribedBy) {
        this.visit = visit;
        this.items = items;
        this.prescribedBy = prescribedBy;
        this.prescribedAt = LocalDateTime.now();
        this.status = "Pending";
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Visit getVisit() { return visit; }
    public void setVisit(Visit visit) { this.visit = visit; }
    public List<PrescriptionItem> getItems() { return items; }
    public void setItems(List<PrescriptionItem> items) { this.items = items; }
    public LocalDateTime getPrescribedAt() { return prescribedAt; }
    public void setPrescribedAt(LocalDateTime prescribedAt) { this.prescribedAt = prescribedAt; }
    public User getPrescribedBy() { return prescribedBy; }
    public void setPrescribedBy(User prescribedBy) { this.prescribedBy = prescribedBy; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}