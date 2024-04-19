package com.api.springcrudbook.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.springcrudbook.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // You can add custom query methods here if needed
    List<Book> findByPublisherName(String publisherName);
    Optional<Book> findById(Long id);
}
