package com.api.springcrudbook.service;



import com.api.springcrudbook.entity.Transaction;

public interface TransactionService {
    Transaction createTransaction(Long userId, Long bookId, int quantity, double totalPrice, String paymentMethod);
}
