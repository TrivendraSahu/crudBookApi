package com.api.springcrudbook.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.api.springcrudbook.entity.User;

import com.api.springcrudbook.dao.BookRepository;
import com.api.springcrudbook.dao.TransactionRepository;
import com.api.springcrudbook.entity.Book;
import com.api.springcrudbook.entity.Transaction;

@Service
public class BookServiceImpl implements BookService {
	
	 @Autowired
	    private TransactionRepository transactionRepository;
	
	@Autowired
	private final BookRepository bookRepository;

	
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book addBook(Book book, MultipartFile imageFile) {
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                book.setImage(imageFile.getBytes());
            }
            return bookRepository.save(book);
        } catch (IOException e) {
            e.printStackTrace();
            return null; // handle error appropriately
        }
    }

    @Override
    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            // Update book details here
            book.setTitle(updatedBook.getTitle());
            book.setPublishingDate(updatedBook.getPublishingDate());
            book.setPublisherName(updatedBook.getPublisherName());
            book.setTotalCopies(updatedBook.getTotalCopies());
            book.setCopiesIssued(updatedBook.getCopiesIssued());
            book.setCopiesAvailable(updatedBook.getCopiesAvailable());
            // Assuming Author should not be updated through this endpoint
            // book.setAuthor(updatedBook.getAuthor());
            return bookRepository.save(book);
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }

    @Override
    public void updateBookImage(Long id, byte[] image) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setImage(image);
            bookRepository.save(book);
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    
   

    public List<Book> getIssuedBooks() {
        // get all transactions
        List<Transaction> transactions = transactionRepository.findAll();

        // create a list of books with user information
        List<Book> issuedBooks = new ArrayList<>();
        for (Transaction transaction : transactions) {
            Book book = transaction.getBook();
            User user = transaction.getUser();

            // add user information to the book
            book.setIssuedBy(user.getFirstName() + " " + user.getLastName());

            issuedBooks.add(book);
        }

        return issuedBooks;
    }
    @Override
    public boolean isAvailable(Long bookId, int quantity) {
        Book book = getBookById(bookId);
        if (book == null) {
            throw new RuntimeException("Book not found");
        }
        return book.isAvailable(quantity);
    }
}

