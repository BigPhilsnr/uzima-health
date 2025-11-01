package com.uzimahealth.model;

import jakarta.persistence.*;

@Entity
public class PrescriptionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;
    private String itemCode;
    private String itemName;
    private int quantity;
    private String dosage;
    private String frequency;
    private String duration;
    private String instructions;

    public PrescriptionItem() {}

    public PrescriptionItem(Prescription prescription, String itemCode, String itemName, int quantity, String dosage,
                            String frequency, String duration, String instructions) {
        this.prescription = prescription;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantity = quantity;
        this.dosage = dosage;
        this.frequency = frequency;
        this.duration = duration;
        this.instructions = instructions;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Prescription getPrescription() { return prescription; }
    public void setPrescription(Prescription prescription) { this.prescription = prescription; }
    public String getItemCode() { return itemCode; }
    public void setItemCode(String itemCode) { this.itemCode = itemCode; }
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }
    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
}