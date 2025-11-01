package com.uzimahealth.repository;

import com.uzimahealth.model.Triage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TriageRepository extends JpaRepository<Triage, Long> {
}