package com.workshop.carauctionsystem.service;

import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;

public interface TransactionService {
    public void saveTransaction(long userId, long carId, String transactionHash, int status, Timestamp createdAt, Timestamp updateAt);
    public Page<Transaction> findAllByName(Pageable pageable, String name);
}
