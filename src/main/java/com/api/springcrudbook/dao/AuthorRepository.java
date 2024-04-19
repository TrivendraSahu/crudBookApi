package com.api.springcrudbook.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.springcrudbook.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // You can define custom query methods if needed
}
