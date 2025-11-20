// src/main/java/com/ecommerce/api/dto/RegisterRequest.java
package com.ecommerce.api.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
}