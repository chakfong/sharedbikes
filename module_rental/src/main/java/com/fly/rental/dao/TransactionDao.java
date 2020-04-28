package com.fly.rental.dao;

import com.fly.rental.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionDao extends JpaRepository<Transaction, Long> {

    public List<Transaction> findTransactionsByUserIdIs(Long userId);
}
