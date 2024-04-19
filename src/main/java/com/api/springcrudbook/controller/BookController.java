package com.api.springcrudbook.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.api.springcrudbook.entity.Author;
import com.api.springcrudbook.entity.Book;
import com.api.springcrudbook.service.BookService;

@RestController
@CrossOrigin(origins="http://localhost:3001")
public class BookController {

    @Autowired
    private final BookService bookService;
    
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        return book != null ?
                new ResponseEntity<>(book, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

   

    @PostMapping("/api/books/add")
    public ResponseEntity<Book> addBook(
            @RequestParam("title") String title,
            @RequestParam("publishingDate") String publishingDate,
            @RequestParam("publisherName") String publisherName,
            @RequestParam("totalCopies") int totalCopies,
            @RequestParam("copiesIssued") int copiesIssued,
            @RequestParam("copiesAvailable") int copiesAvailable,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("image") MultipartFile image
    ) {
        try {
            Book book = new Book();
            book.setTitle(title);
            // Convert publishingDate string to LocalDate if needed
            book.setPublishingDate(LocalDate.parse(publishingDate));
            book.setPublisherName(publisherName);
            book.setTotalCopies(totalCopies);
            book.setCopiesIssued(copiesIssued);
            book.setCopiesAvailable(copiesAvailable);
            // Set author details
            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);
            book.setAuthor(author);
            // Save the book along with the image
            Book savedBook = bookService.addBook(book, image);
            return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/api/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book book = bookService.updateBook(id, updatedBook);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<String> updateBookImage(@PathVariable Long id, @RequestBody byte[] image) {
        bookService.updateBookImage(id, image);
        return ResponseEntity.ok("Image updated successfully");
    }
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
