package com.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.entities.Product;
import com.domain.entities.Supplier;
import com.domain.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierService supplierService;

    public Product save(Product product){
        return productRepository.save(product);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findOne(Long Id){
        Optional<Product> product = productRepository.findById(Id);

        if (!product.isPresent()){
            return null;
        }

        return product.get();
    }

    public void removeOne(Long Id){
        productRepository.deleteById(Id);
    }

    public List<Product> findByName(String name){
        return productRepository.findByNameContains(name);
    }

    // Many to Many Bidirectional (Product and Supplier)
    public void addSupplier(Supplier supplier, Long productId){
        Product product = findOne(productId);

        if (product == null){
            throw new RuntimeException("Product with ID: "  + productId + " not found");
        }

        product.getSuppliers().add(supplier);

        save(product);
    }

    public Product findProductByName(String name){
        return productRepository.findProductByName(name);
    }

    public List<Product> findProductByNameLike(String name){
        return productRepository.findProductByNameLike("%" +name + "%");
    }

    public List<Product> findProductByCategory(Long categoryId){
        return productRepository.findProductByCategory(categoryId);
    }

    public List<Product> findProductBySupplier(Long supplierId){
        Supplier supplier = supplierService.findOne(supplierId);

        if(supplier == null){
            return new ArrayList<Product>();
        }

        return productRepository.findProductBySupplier(supplier);
    }
}
