package com.api.springcrudbook.entity;

import lombok.Data;

@Data
public class UserLoginRequest {

    private String username;
    private String password;

    // Getters and Setters (Omitted for brevity)
}
