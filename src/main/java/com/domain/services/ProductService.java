package com.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.entities.Product;
import com.domain.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> findProduct(Long Id){
        return productRepository.findById(Id);
    }

    public void deleteProduct(Long Id){
        productRepository.deleteById(Id);
    }

    public List<Product> findByNameProducts(String name){
        return productRepository.findByNameContains(name);
    }
}
