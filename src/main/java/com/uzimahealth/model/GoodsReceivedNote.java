package com.uzimahealth.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class GoodsReceivedNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String grnNumber;
    @ManyToOne
    @JoinColumn(name = "po_id")
    private PurchaseOrder purchaseOrder;
    private LocalDateTime receivedDate;
    @OneToMany(mappedBy = "grn", cascade = CascadeType.ALL)
    private List<GoodsReceivedItem> items;
    @ManyToOne
    @JoinColumn(name = "received_by")
    private User receivedBy;
    private String notes;

    public GoodsReceivedNote() {}

    public GoodsReceivedNote(String grnNumber, PurchaseOrder purchaseOrder, List<GoodsReceivedItem> items, User receivedBy, String notes) {
        this.grnNumber = grnNumber;
        this.purchaseOrder = purchaseOrder;
        this.items = items;
        this.receivedBy = receivedBy;
        this.notes = notes;
        this.receivedDate = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getGrnNumber() { return grnNumber; }
    public void setGrnNumber(String grnNumber) { this.grnNumber = grnNumber; }
    public PurchaseOrder getPurchaseOrder() { return purchaseOrder; }
    public void setPurchaseOrder(PurchaseOrder purchaseOrder) { this.purchaseOrder = purchaseOrder; }
    public LocalDateTime getReceivedDate() { return receivedDate; }
    public void setReceivedDate(LocalDateTime receivedDate) { this.receivedDate = receivedDate; }
    public List<GoodsReceivedItem> getItems() { return items; }
    public void setItems(List<GoodsReceivedItem> items) { this.items = items; }
    public User getReceivedBy() { return receivedBy; }
    public void setReceivedBy(User receivedBy) { this.receivedBy = receivedBy; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}