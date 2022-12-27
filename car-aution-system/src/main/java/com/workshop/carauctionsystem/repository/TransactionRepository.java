package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Brand;
import com.workshop.carauctionsystem.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO `swp490_cab`.`transaction` (`userId`, `carId`, `transactionHash`, `status`, `createdAt`, `updateAt`) VALUES (:userId, :carId, :transactionHash, :status, :createdAt, :updateAt)")
    public void saveTransaction(long userId, long carId, String transactionHash, int status, Timestamp createdAt, Timestamp updateAt);

    @Query(nativeQuery = true, value = "select * from transaction order by id")
    public Page<Transaction> findAll(Pageable pageable);

    @Query(nativeQuery = true, value = "select * \n" +
            "from transaction\n" +
            "where transaction.transactionHash like %?1% \n" +
            "order by id")
    public Page<Transaction> findAllByName(Pageable pageable, String name);
}
