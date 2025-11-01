package com.uzimahealth.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;
    @Column(length = 2000)
    private String symptoms;
    @Column(length = 2000)
    private String examinationFindings;
    private String diagnosis; // ICD-10 code
    @Column(length = 2000)
    private String clinicalNotes;
    private String treatmentPlan;
    private LocalDateTime consultationTime;
    @ManyToOne
    @JoinColumn(name = "consulted_by")
    private User consultedBy;

    public Consultation() {}

    public Consultation(Visit visit, String symptoms, String examinationFindings, String diagnosis,
                        String clinicalNotes, String treatmentPlan, User consultedBy) {
        this.visit = visit;
        this.symptoms = symptoms;
        this.examinationFindings = examinationFindings;
        this.diagnosis = diagnosis;
        this.clinicalNotes = clinicalNotes;
        this.treatmentPlan = treatmentPlan;
        this.consultedBy = consultedBy;
        this.consultationTime = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Visit getVisit() { return visit; }
    public void setVisit(Visit visit) { this.visit = visit; }
    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }
    public String getExaminationFindings() { return examinationFindings; }
    public void setExaminationFindings(String examinationFindings) { this.examinationFindings = examinationFindings; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public String getClinicalNotes() { return clinicalNotes; }
    public void setClinicalNotes(String clinicalNotes) { this.clinicalNotes = clinicalNotes; }
    public String getTreatmentPlan() { return treatmentPlan; }
    public void setTreatmentPlan(String treatmentPlan) { this.treatmentPlan = treatmentPlan; }
    public LocalDateTime getConsultationTime() { return consultationTime; }
    public void setConsultationTime(LocalDateTime consultationTime) { this.consultationTime = consultationTime; }
    public User getConsultedBy() { return consultedBy; }
    public void setConsultedBy(User consultedBy) { this.consultedBy = consultedBy; }
}