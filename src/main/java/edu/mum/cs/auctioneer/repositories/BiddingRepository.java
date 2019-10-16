package edu.mum.cs.auctioneer.repositories;

import edu.mum.cs.auctioneer.models.Bidding;
import edu.mum.cs.auctioneer.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiddingRepository extends JpaRepository<Bidding, Long> {
    Bidding findTopByPostOrderByPriceDesc(Post post);
}
