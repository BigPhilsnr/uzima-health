package com.uzimahealth.stock;

import jakarta.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String itemCode;
    private String name;
    private String category; // Drug, Lab Reagent, Non-Drug
    private String unitOfMeasure; // Tablets, ml, pieces
    private String supplier;
    private int reorderLevel;
    private double unitPrice;
    private String description;

    public Item() {}

    public Item(String itemCode, String name, String category, String unitOfMeasure, String supplier,
                int reorderLevel, double unitPrice, String description) {
        this.itemCode = itemCode;
        this.name = name;
        this.category = category;
        this.unitOfMeasure = unitOfMeasure;
        this.supplier = supplier;
        this.reorderLevel = reorderLevel;
        this.unitPrice = unitPrice;
        this.description = description;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getItemCode() { return itemCode; }
    public void setItemCode(String itemCode) { this.itemCode = itemCode; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getUnitOfMeasure() { return unitOfMeasure; }
    public void setUnitOfMeasure(String unitOfMeasure) { this.unitOfMeasure = unitOfMeasure; }
    public String getSupplier() { return supplier; }
    public void setSupplier(String supplier) { this.supplier = supplier; }
    public int getReorderLevel() { return reorderLevel; }
    public void setReorderLevel(int reorderLevel) { this.reorderLevel = reorderLevel; }
    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}