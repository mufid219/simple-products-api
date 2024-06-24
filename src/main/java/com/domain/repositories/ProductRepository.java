package com.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.domain.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContains(String name);
}
