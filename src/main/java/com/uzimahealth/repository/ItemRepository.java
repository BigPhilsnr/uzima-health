package com.uzimahealth.repository;

import com.uzimahealth.stock.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}