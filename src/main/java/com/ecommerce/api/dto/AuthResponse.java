// src/main/java/com/ecommerce/api/dto/AuthResponse.java
package com.ecommerce.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String username;
}