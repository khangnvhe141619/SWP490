package com.workshop.carautionsystem.repository;

import com.workshop.carautionsystem.model.Auction;
import com.workshop.carautionsystem.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends MongoRepository<Auction,String>{

	Auction findByProduct(Product product);
}
