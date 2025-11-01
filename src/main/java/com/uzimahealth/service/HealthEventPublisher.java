package com.uzimahealth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Configuration
public class HealthEventPublisher {

    private Map<String, Object> pendingEvent;

    @Bean
    public Supplier<Message<Map<String, Object>>> healthEvents() {
        return () -> {
            if (pendingEvent != null) {
                Message<Map<String, Object>> message = MessageBuilder.withPayload(pendingEvent).build();
                pendingEvent = null;
                return message;
            }
            return null;
        };
    }

    public void publishPrescriptionDispensed(String prescriptionNo, String itemCode, String itemName,
                                           int quantity, String warehouse, String user) {
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("eventType", "PRESCRIPTION_DISPENSED");
        eventData.put("prescriptionNo", prescriptionNo);
        eventData.put("itemCode", itemCode);
        eventData.put("itemName", itemName);
        eventData.put("quantity", quantity);
        eventData.put("warehouse", warehouse);
        eventData.put("user", user);

        this.pendingEvent = eventData;
        System.out.println("Prepared prescription dispensed event for: " + itemCode);
    }

    public void publishStockAdjustment(String adjustmentNo, String itemCode, int quantity,
                                     String warehouse, String adjustmentType, String user) {
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("eventType", "STOCK_ADJUSTMENT");
        eventData.put("adjustmentNo", adjustmentNo);
        eventData.put("itemCode", itemCode);
        eventData.put("quantity", quantity);
        eventData.put("warehouse", warehouse);
        eventData.put("type", adjustmentType); // "in" or "out"
        eventData.put("user", user);

        this.pendingEvent = eventData;
        System.out.println("Prepared stock adjustment event for: " + itemCode);
    }
}