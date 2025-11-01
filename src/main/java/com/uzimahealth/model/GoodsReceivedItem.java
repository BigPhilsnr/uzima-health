package com.uzimahealth.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class GoodsReceivedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "grn_id")
    private GoodsReceivedNote grn;
    private String itemCode;
    private int receivedQuantity;
    private int acceptedQuantity;
    private String batchNumber;
    private LocalDate expiryDate;
    private double unitCost;
    private String remarks;

    public GoodsReceivedItem() {}

    public GoodsReceivedItem(GoodsReceivedNote grn, String itemCode, int receivedQuantity, int acceptedQuantity,
                             String batchNumber, LocalDate expiryDate, double unitCost, String remarks) {
        this.grn = grn;
        this.itemCode = itemCode;
        this.receivedQuantity = receivedQuantity;
        this.acceptedQuantity = acceptedQuantity;
        this.batchNumber = batchNumber;
        this.expiryDate = expiryDate;
        this.unitCost = unitCost;
        this.remarks = remarks;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public GoodsReceivedNote getGrn() { return grn; }
    public void setGrn(GoodsReceivedNote grn) { this.grn = grn; }
    public String getItemCode() { return itemCode; }
    public void setItemCode(String itemCode) { this.itemCode = itemCode; }
    public int getReceivedQuantity() { return receivedQuantity; }
    public void setReceivedQuantity(int receivedQuantity) { this.receivedQuantity = receivedQuantity; }
    public int getAcceptedQuantity() { return acceptedQuantity; }
    public void setAcceptedQuantity(int acceptedQuantity) { this.acceptedQuantity = acceptedQuantity; }
    public String getBatchNumber() { return batchNumber; }
    public void setBatchNumber(String batchNumber) { this.batchNumber = batchNumber; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    public double getUnitCost() { return unitCost; }
    public void setUnitCost(double unitCost) { this.unitCost = unitCost; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}