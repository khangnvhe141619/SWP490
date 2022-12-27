package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.entity.Transaction;
import com.workshop.carauctionsystem.repository.TransactionRepository;
import com.workshop.carauctionsystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public void saveTransaction(long userId, long carId, String transactionHash, int status, Timestamp createdAt, Timestamp updateAt) {
        transactionRepository.saveTransaction(userId, carId, transactionHash, status, createdAt, updateAt);
    }

    @Override
    public Page<Transaction> findAllByName(Pageable pageable, String name) {
        if (name != null) {
            return transactionRepository.findAllByName(pageable, name);
        }else {
            return transactionRepository.findAll(pageable);
        }
    }
}
