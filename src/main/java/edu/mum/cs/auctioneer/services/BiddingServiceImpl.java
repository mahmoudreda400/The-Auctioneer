package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Bidding;
import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.User;
import edu.mum.cs.auctioneer.repositories.BiddingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class BiddingServiceImpl implements BiddingService {

    private BiddingRepository biddingRepository;

    @Autowired
    public BiddingServiceImpl(BiddingRepository biddingRepository) {
        this.biddingRepository = biddingRepository;
    }

    @Override
    public ResponseEntity getMaxBiddingOnPost(Post post) {
        Bidding responseBody = getBiddingRepository().findTopByPostOrderByPriceDesc(post);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Bidding> bid(Post post, User user) {
        Bidding lastBidding = (Bidding) getMaxBiddingOnPost(post).getBody();
        Double newPrice = lastBidding.getPrice() + post.getIncrValue();

        Bidding bidding = new Bidding();
        bidding.setPost(post);
        bidding.setUser(user);
        bidding.setDate(LocalTime.now());
        bidding.setPrice(newPrice);

        Bidding responseBody = getBiddingRepository().save(bidding);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    //------------------------setters and getters-----------------

    public BiddingRepository getBiddingRepository() {
        return biddingRepository;
    }

    public void setBiddingRepository(BiddingRepository biddingRepository) {
        this.biddingRepository = biddingRepository;
    }
    
	@Override
	public List<Bidding> getUserNotificatios(long userId){
		return biddingRepository.findTopByExpirDateLessThanEqualOrderBypriceDesc( LocalDate.now());
	}
}
