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

import com.domain.dto.CategoryDataDTO;
import com.domain.dto.ResponseData;
import com.domain.entities.Category;
import com.domain.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
  
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> create(@Valid @RequestBody CategoryDataDTO categoryDataDTO, Errors errors){

        ResponseData<Category> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Category category = new Category();
        category.setName(categoryDataDTO.getName());

        responseData.setStatus(true);
        responseData.setMessages(null);
        responseData.setPayload(categoryService.save(category));

        return ResponseEntity.ok(responseData);
    }

    @GetMapping
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category findOne(@PathVariable Long id){
        return categoryService.findOne(id);
    }

    @PutMapping
    public ResponseEntity<ResponseData<Category>> update(@Valid @RequestBody CategoryDataDTO categoryDataDTO, Errors errors){

        ResponseData<Category> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        Category category = new Category();
        category.setName(categoryDataDTO.getName());

        responseData.setStatus(true);
        responseData.setMessages(null);
        responseData.setPayload(categoryService.save(category));

        return ResponseEntity.ok(responseData);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable Long id){
        categoryService.removeOne(id);
    }
}
