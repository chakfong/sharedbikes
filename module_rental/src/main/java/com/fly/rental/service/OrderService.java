package com.fly.rental.service;

import com.fly.rental.dao.TransactionDao;
import com.fly.rental.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    TransactionDao transactionDao;

    public List<Transaction> getTransactionsByUserId(Long userId){
        return transactionDao.findTransactionsByUserIdIs(userId);
    }
}
