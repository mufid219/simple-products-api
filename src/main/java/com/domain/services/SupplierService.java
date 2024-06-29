package com.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.entities.Supplier;
import com.domain.repositories.SupplierRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SupplierService {
    
    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    public Supplier findOne(Long id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);

        if(!supplier.isPresent()){
            return null;
        }

        return supplier.get();
    }

    public void removeOne(Long id){
        supplierRepository.deleteById(id);
    }
}
