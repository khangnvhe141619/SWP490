package com.workshop.carautionsystem.service.impl;

import com.workshop.carautionsystem.model.Auction;
import com.workshop.carautionsystem.model.Product;
import com.workshop.carautionsystem.model.User;
import com.workshop.carautionsystem.repository.AuctionRepository;
import com.workshop.carautionsystem.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService {

	@Autowired
	AuctionRepository AuctionRepository;
	
	@Autowired
	MongoOperations mongoOperations;
 	
	@Override
	public Auction getById(String id) throws Exception {
		return AuctionRepository.findOne(id);
	}

	@Override
	public Auction addNewAuction(Auction auction) throws Exception {
		auction.setParticipants(new java.util.HashSet<User>());
		auction.setBiddings(new java.util.ArrayList<BidInformation>());
		return AuctionRepository.insert(auction);
	}

	@Override
	public Auction updateAuction(String id, Auction auction) throws Exception {
		if(AuctionRepository.exists(id))
			return AuctionRepository.save(auction);
		throw new RuntimeException("No such Auction exists with Auction id -> "+id);
	}

	@Override
	public Auction deleteAuction(String id) throws Exception {
		if(AuctionRepository.exists(id)) {
			Auction u = AuctionRepository.findOne(id);
			AuctionRepository.delete(id);
			return u;
		}
		throw new RuntimeException("No such Auction exists with Auction id -> "+id);
	}
	
	@Override
	public Auction getByProduct(Product product) throws Exception {
		Auction u = AuctionRepository.findByProduct(product);
		if(u != null)
			return u;
		throw new RuntimeException("No such auction exists for product ->"+product);
	}

	@Override
	public List<Auction> getAllActive() throws Exception {
		Query query = new Query();
		//query.addCriteria(Criteria.where("endTime").gt(System.currentTimeMillis())); 
		List<Auction> list = mongoOperations.find(query,Auction.class);
		return list;
	}
}