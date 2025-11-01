package com.uzimahealth.service;

import com.uzimahealth.model.*;
import com.uzimahealth.repository.*;
import com.uzimahealth.stock.Item;
import com.uzimahealth.stock.Stock;
import com.uzimahealth.stock.StockLedger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private StockLedgerRepository stockLedgerRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;

    public void addStock(Long itemId, String batchNumber, String expiryDate, int quantity, String store, double unitCost) {
        Optional<Item> item = itemRepository.findById(itemId);
        if (item.isPresent()) {
            Stock stock = new Stock(item.get(), batchNumber, java.time.LocalDate.parse(expiryDate), quantity, store, unitCost);
            stockRepository.save(stock);
            // Log to ledger
            logStockTransaction(item.get(), "Receipt", quantity, batchNumber, "Stock Addition", store, 1L); // Assuming user id 1
        }
    }

    public void deductStock(Long itemId, int quantity, String store, String reference, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Item> item = itemRepository.findById(itemId);
        if (user.isPresent() && item.isPresent()) {
            // Find available stock (simplified - deduct from first available)
            List<Stock> stocks = stockRepository.findAll().stream()
                    .filter(s -> s.getItem().getId().equals(itemId) && s.getStore().equals(store) && s.getQuantity() > 0)
                    .toList();
            int remaining = quantity;
            for (Stock stock : stocks) {
                if (remaining <= 0) break;
                int deduct = Math.min(remaining, stock.getQuantity());
                stock.setQuantity(stock.getQuantity() - deduct);
                stockRepository.save(stock);
                remaining -= deduct;
                logStockTransaction(item.get(), "Issue", -deduct, stock.getBatchNumber(), reference, store, userId);
            }
        }
    }

    private void logStockTransaction(Item item, String type, int quantity, String batch, String reference, String store, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            StockLedger ledger = new StockLedger(item, type, quantity, batch, reference, store, user.get(), "");
            stockLedgerRepository.save(ledger);
        }
    }

    public List<Stock> getStockByStore(String store) {
        return stockRepository.findAll().stream()
                .filter(s -> s.getStore().equals(store))
                .toList();
    }

    public int getTotalStock(Long itemId, String store) {
        return stockRepository.findAll().stream()
                .filter(s -> s.getItem().getId().equals(itemId) && s.getStore().equals(store))
                .mapToInt(Stock::getQuantity)
                .sum();
    }
}