package com.workshop.carautionsystem.service;

import com.workshop.carautionsystem.model.Auction;
import com.workshop.carautionsystem.model.Product;

import java.util.List;

public interface AuctionService {
	
	Auction getById(String id)throws Exception;
	
	List<Auction> getAllActive() throws Exception;
	
	Auction addNewAuction(Auction auction)throws Exception;
	
	Auction updateAuction(String id,Auction auction)throws Exception;
	
	Auction deleteAuction(String id)throws Exception; 

	Auction getByProduct(Product product) throws Exception;
}
