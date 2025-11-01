package com.uzimahealth.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Triage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "visit_id")
    private Visit visit;
    private double bloodPressureSystolic;
    private double bloodPressureDiastolic;
    private int heartRate;
    private double temperature;
    private int respiratoryRate;
    private double weight;
    private double height;
    private double bmi;
    private String triageCategory; // Emergency, Priority, Routine
    private String notes;
    private LocalDateTime recordedAt;
    @ManyToOne
    @JoinColumn(name = "recorded_by")
    private User recordedBy;

    public Triage() {}

    public Triage(Visit visit, double bloodPressureSystolic, double bloodPressureDiastolic, int heartRate,
                  double temperature, int respiratoryRate, double weight, double height, String triageCategory,
                  String notes, User recordedBy) {
        this.visit = visit;
        this.bloodPressureSystolic = bloodPressureSystolic;
        this.bloodPressureDiastolic = bloodPressureDiastolic;
        this.heartRate = heartRate;
        this.temperature = temperature;
        this.respiratoryRate = respiratoryRate;
        this.weight = weight;
        this.height = height;
        this.bmi = calculateBMI();
        this.triageCategory = triageCategory;
        this.notes = notes;
        this.recordedBy = recordedBy;
        this.recordedAt = LocalDateTime.now();
    }

    private double calculateBMI() {
        if (height > 0) {
            return weight / (height * height);
        }
        return 0;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Visit getVisit() { return visit; }
    public void setVisit(Visit visit) { this.visit = visit; }
    public double getBloodPressureSystolic() { return bloodPressureSystolic; }
    public void setBloodPressureSystolic(double bloodPressureSystolic) { this.bloodPressureSystolic = bloodPressureSystolic; }
    public double getBloodPressureDiastolic() { return bloodPressureDiastolic; }
    public void setBloodPressureDiastolic(double bloodPressureDiastolic) { this.bloodPressureDiastolic = bloodPressureDiastolic; }
    public int getHeartRate() { return heartRate; }
    public void setHeartRate(int heartRate) { this.heartRate = heartRate; }
    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    public int getRespiratoryRate() { return respiratoryRate; }
    public void setRespiratoryRate(int respiratoryRate) { this.respiratoryRate = respiratoryRate; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; this.bmi = calculateBMI(); }
    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; this.bmi = calculateBMI(); }
    public double getBmi() { return bmi; }
    public void setBmi(double bmi) { this.bmi = bmi; }
    public String getTriageCategory() { return triageCategory; }
    public void setTriageCategory(String triageCategory) { this.triageCategory = triageCategory; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }
    public User getRecordedBy() { return recordedBy; }
    public void setRecordedBy(User recordedBy) { this.recordedBy = recordedBy; }
}