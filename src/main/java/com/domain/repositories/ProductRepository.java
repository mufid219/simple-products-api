package com.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.domain.entities.Product;
import com.domain.entities.Supplier;

import jakarta.websocket.server.PathParam;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContains(String name);

    @Query("SELECT p from Product p WHERE p.name = :name")
    public Product findProductByName(@PathParam("name") String name);

    @Query("SELECT p from Product p WHERE p.name LIKE :name")
    public List<Product> findProductByNameLike(@PathParam("name") String name);

    @Query("SELECT p from Product p WHERE p.category.id = :categoryId")
    public List<Product> findProductByCategory(@PathParam("categoryId") Long categoryId);

    @Query("SELECT p from Product p WHERE :supplier MEMBER OF p.suppliers")
    public List<Product> findProductBySupplier(@PathParam("supplier") Supplier supplier);
}
