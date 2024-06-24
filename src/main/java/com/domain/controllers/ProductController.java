package com.domain.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.domain.entities.Product;
import com.domain.services.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping()
    public Product createProduct(@RequestBody Product product) {
        
        return productService.saveProduct(product);
    }
    
    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<Product> getSingleProduct(@PathVariable("id") Long id){
        return productService.findProduct(id);
    }

    @PutMapping()
    public Product updateProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }


}
