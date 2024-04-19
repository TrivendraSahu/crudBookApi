package com.api.springcrudbook.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.springcrudbook.dao.BookRepository;
import com.api.springcrudbook.dao.TransactionRepository;
import com.api.springcrudbook.dao.UserRepository;
import com.api.springcrudbook.entity.Book;
import com.api.springcrudbook.entity.Transaction;
import com.api.springcrudbook.entity.User;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

   
    public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Transaction createTransaction(Long userId, Long bookId, int quantity, double totalPrice, String paymentMethod) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setBook(book);
        transaction.setQuantity(quantity);
        transaction.setTotalPrice(totalPrice);
        transaction.setPaymentMethod(paymentMethod);
       

        // Update book information
        int remainingCopies = book.getCopiesAvailable() - quantity;
        book.setCopiesAvailable(remainingCopies);
        int issuedCopies = book.getCopiesIssued() + quantity;
        book.setCopiesIssued(issuedCopies);
        bookRepository.save(book);

        return transactionRepository.save(transaction);
    }
}
