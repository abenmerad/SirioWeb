package com.sirioitalia.api.repository;

import com.sirioitalia.api.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillingRepository
        extends JpaRepository<Billing, Integer> {

    @Query("SELECT b FROM Billing b where b.orderNumber = ?1")
    Optional<Billing> findBillingByOrderNumber(Long orderNumber);
}