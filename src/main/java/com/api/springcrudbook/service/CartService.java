package com.api.springcrudbook.service;

import java.util.List;

import com.api.springcrudbook.entity.Cart;

public interface CartService {
    Cart addToCart(Long bookId, int quantity, Long userId);
    List<Cart> getCartItems(Long userId);
    void updateCartItem(Long cartItemId, int quantity);
    void removeFromCart(Long cartItemId);
    
    
}
