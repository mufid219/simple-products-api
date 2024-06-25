package com.domain.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RestController;

import com.domain.dto.ResponseData;
import com.domain.entities.Product;
import com.domain.services.ProductService;

import jakarta.validation.Valid;

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
    public ResponseEntity<ResponseData<Product>> createProduct(@Valid @RequestBody Product product, Errors errors) {

        ResponseData<Product> responseData = new ResponseData<>();;

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setMessages(null);
        responseData.setStatus(true);
        responseData.setPayload(productService.saveProduct(product));
        
        return ResponseEntity.ok(responseData);
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
    public ResponseEntity<ResponseData<Product>> updateProduct(@Valid @RequestBody Product product, Errors errors) {
        ResponseData<Product> responseData = new ResponseData<>();;

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setMessages(null);
        responseData.setStatus(true);
        responseData.setPayload(productService.saveProduct(product));
        
        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productService.deleteProduct(id);
    }


}
