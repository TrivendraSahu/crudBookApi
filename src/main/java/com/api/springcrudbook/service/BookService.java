package com.api.springcrudbook.service;

import java.util.List;


import org.springframework.web.multipart.MultipartFile;
import com.api.springcrudbook.entity.Book;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book addBook(Book book, MultipartFile imageFile);
    Book updateBook(Long id, Book updatedBook);
    void updateBookImage(Long id, byte[] image);
    void deleteBook(Long id);
    boolean isAvailable(Long bookId, int quantity);
}
