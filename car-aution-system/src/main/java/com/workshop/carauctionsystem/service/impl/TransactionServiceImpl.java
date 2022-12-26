package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.repository.TransactionRepository;
import com.workshop.carauctionsystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
