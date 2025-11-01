package com.uzimahealth.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationalId;
    private String phone;
    private String nhifNumber;
    private String insuranceProvider;
    private String paymentType; // Cash, Insurance, NHIF
    private int age;
    @Column(length = 1000)
    private String medicalHistory;
    private String contactInfo;

    public Patient() {}

    public Patient(String name, LocalDate dateOfBirth, String gender, String nationalId, String phone,
                   String nhifNumber, String insuranceProvider, String paymentType, String medicalHistory, String contactInfo) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nationalId = nationalId;
        this.phone = phone;
        this.nhifNumber = nhifNumber;
        this.insuranceProvider = insuranceProvider;
        this.paymentType = paymentType;
        this.medicalHistory = medicalHistory;
        this.contactInfo = contactInfo;
        this.age = calculateAge();
    }

    private int calculateAge() {
        if (dateOfBirth != null) {
            return LocalDate.now().getYear() - dateOfBirth.getYear();
        }
        return age;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; this.age = calculateAge(); }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getNationalId() { return nationalId; }
    public void setNationalId(String nationalId) { this.nationalId = nationalId; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getNhifNumber() { return nhifNumber; }
    public void setNhifNumber(String nhifNumber) { this.nhifNumber = nhifNumber; }
    public String getInsuranceProvider() { return insuranceProvider; }
    public void setInsuranceProvider(String insuranceProvider) { this.insuranceProvider = insuranceProvider; }
    public String getPaymentType() { return paymentType; }
    public void setPaymentType(String paymentType) { this.paymentType = paymentType; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getMedicalHistory() { return medicalHistory; }
    public void setMedicalHistory(String medicalHistory) { this.medicalHistory = medicalHistory; }
    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
}