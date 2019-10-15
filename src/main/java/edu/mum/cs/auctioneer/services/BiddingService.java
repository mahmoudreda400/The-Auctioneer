package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Bidding;
import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.User;
import org.springframework.http.ResponseEntity;

public interface BiddingService {
    ResponseEntity<Bidding> getMaxBiddingOnPost(Post post);
    ResponseEntity<Bidding> bid(Post post, User user);
}
