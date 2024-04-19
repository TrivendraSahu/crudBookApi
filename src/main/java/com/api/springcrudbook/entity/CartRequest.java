package com.api.springcrudbook.entity;


import lombok.Data;

@Data
public class CartRequest {
    private Long bookId;
    private int quantity;
    private Long userId;
}
