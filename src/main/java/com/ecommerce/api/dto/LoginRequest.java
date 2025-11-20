// src/main/java/com/ecommerce/api/dto/LoginRequest.java
package com.ecommerce.api.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}