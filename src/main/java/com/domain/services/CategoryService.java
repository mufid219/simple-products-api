package com.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.entities.Category;
import com.domain.repositories.CategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findOne(Long id){
        Optional<Category> category = categoryRepository.findById(id);

        if(!category.isPresent()){
            return null;
        }

        return category.get();
    }

    public void removeOne(Long id){
        categoryRepository.deleteById(id);
    }
}
