package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Bidding;
import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.User;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface BiddingService {
    ResponseEntity<Bidding> getMaxBiddingOnPost(Post post);
	List<Bidding> getUserNotificatios(long userId);
    ResponseEntity<Bidding> bid(Post post);
}
