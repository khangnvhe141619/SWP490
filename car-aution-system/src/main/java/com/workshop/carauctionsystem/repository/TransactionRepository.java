package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO `swp490_cab`.`transaction` (`userId`, `carId`, `transactionHash`, `status`, `createdAt`, `updateAt`) VALUES (:userId, :carId, :transactionHash, :status, :createdAt, :updateAt)")
    public void saveTransaction(long userId, long carId, String transactionHash, int status, Timestamp createdAt, Timestamp updateAt);
}
