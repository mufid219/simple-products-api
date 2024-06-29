package com.domain.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.dto.ResponseData;
import com.domain.dto.SupplierDataDTO;
import com.domain.entities.Supplier;
import com.domain.services.SupplierService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/suppliers")
public class SupplierController {
    
    @Autowired
    private SupplierService supplierService;

    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierDataDTO supplierDataDTO, Errors errors) {

        ResponseData<Supplier> responseData = new ResponseData<>();
 
        if (errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Supplier supplier = new Supplier();
        supplier.setName(supplierDataDTO.getName());
        supplier.setAddress(supplierDataDTO.getAddress());
        supplier.setEmail(supplierDataDTO.getEmail());

        responseData.setMessages(null);
        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));

        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public List<Supplier> findAll(){
        return supplierService.findAll();
    }

    @GetMapping("/{id}")
    public Supplier findOne(@PathVariable(name = "id") Long id) {
        return supplierService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierDataDTO supplierDataDTO, Errors errors){

        ResponseData<Supplier> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Supplier supplier = new Supplier();
        supplier.setName(supplierDataDTO.getName());
        supplier.setAddress(supplierDataDTO.getAddress());
        supplier.setEmail(supplierDataDTO.getEmail());

        responseData.setStatus(true);
        responseData.setMessages(null);
        responseData.setPayload(supplierService.save(supplier));

        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id){
        supplierService.removeOne(id);
    }
    
    
}
