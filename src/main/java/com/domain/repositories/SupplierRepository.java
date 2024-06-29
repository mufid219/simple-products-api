package com.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.entities.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    
}
