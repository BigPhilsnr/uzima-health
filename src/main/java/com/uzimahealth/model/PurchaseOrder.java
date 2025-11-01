package com.uzimahealth.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String poNumber;
    private String supplier;
    private LocalDateTime orderDate;
    private String status; // Pending, Approved, Ordered, Received
    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
    private List<PurchaseOrderItem> items;
    @ManyToOne
    @JoinColumn(name = "ordered_by")
    private User orderedBy;
    private double totalAmount;

    public PurchaseOrder() {}

    public PurchaseOrder(String poNumber, String supplier, List<PurchaseOrderItem> items, User orderedBy) {
        this.poNumber = poNumber;
        this.supplier = supplier;
        this.items = items;
        this.orderedBy = orderedBy;
        this.orderDate = LocalDateTime.now();
        this.status = "Pending";
        this.totalAmount = calculateTotal();
    }

    private double calculateTotal() {
        return items != null ? items.stream().mapToDouble(item -> item.getQuantity() * item.getUnitPrice()).sum() : 0;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPoNumber() { return poNumber; }
    public void setPoNumber(String poNumber) { this.poNumber = poNumber; }
    public String getSupplier() { return supplier; }
    public void setSupplier(String supplier) { this.supplier = supplier; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<PurchaseOrderItem> getItems() { return items; }
    public void setItems(List<PurchaseOrderItem> items) { this.items = items; this.totalAmount = calculateTotal(); }
    public User getOrderedBy() { return orderedBy; }
    public void setOrderedBy(User orderedBy) { this.orderedBy = orderedBy; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}