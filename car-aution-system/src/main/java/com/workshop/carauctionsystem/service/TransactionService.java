package com.workshop.carauctionsystem.service;

import java.sql.Timestamp;

public interface TransactionService {
    public void saveTransaction(long userId, long carId, String transactionHash, int status, Timestamp createdAt, Timestamp updateAt);
}
