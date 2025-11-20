// src/main/java/com/ecommerce/api/dto/ProductRequest.java
package com.ecommerce.api.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private Integer stock;
}