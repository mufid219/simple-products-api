package com.domain.dto;

import jakarta.validation.constraints.NotEmpty;

public class CategoryDataDTO {
    
    @NotEmpty(message = "name is required")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
