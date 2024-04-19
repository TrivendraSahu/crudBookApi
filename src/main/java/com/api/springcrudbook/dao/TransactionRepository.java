package com.api.springcrudbook.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.springcrudbook.entity.Transaction;



public interface TransactionRepository extends JpaRepository<Transaction,Long> {

}
