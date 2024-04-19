package com.api.springcrudbook.controller;

import com.api.springcrudbook.entity.Transaction;
import com.api.springcrudbook.service.TransactionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:3001")
public class TransactionController {

    private final TransactionService transactionService;

   
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/api/transaction")
    public ResponseEntity<Transaction> createTransaction(
            @RequestParam Long userId,
            @RequestParam Long bookId,
            @RequestParam int quantity,
            @RequestParam double totalPrice,
            @RequestParam String paymentMethod
    ) {
        try {
            Transaction transaction = transactionService.createTransaction(userId, bookId, quantity, totalPrice, paymentMethod);
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
