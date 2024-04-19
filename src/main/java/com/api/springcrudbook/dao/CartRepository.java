package com.api.springcrudbook.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.springcrudbook.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

	List<Cart> findByUserId(Long userId);

}
