package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Bidding;
import edu.mum.cs.auctioneer.models.PersonType;
import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.User;
import edu.mum.cs.auctioneer.repositories.BiddingRepository;
import edu.mum.cs.auctioneer.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class BiddingServiceImpl implements BiddingService {

    private BiddingRepository biddingRepository;
    private PostService postService;
    private UserService userService;

    @Autowired
    public BiddingServiceImpl(BiddingRepository biddingRepository, PostService postService, UserService userService) {
        this.biddingRepository = biddingRepository;
        this.postService = postService;
        this.userService = userService;
    }

    @Override
    public ResponseEntity getMaxBiddingOnPost(Post post) {
        Optional<Post> targetPost = getPostService().getPostById(post.getId());
        if(targetPost.isPresent()){
            Bidding responseBody = getBiddingRepository().findTopByPostOrderByPriceDesc(targetPost.get());
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }
        return new ResponseEntity("post with id: "+post.getId()+" is not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Bidding> bid(Post post) {
        User user = new User();
        if (null == UserSession.getLoggedInPerson() || !PersonType.user.equals(UserSession.getLoggedInPerson().getRole())){
            return new ResponseEntity("please login before you can bid!", HttpStatus.FORBIDDEN);
        }

        user = getUserService().findByEmail(UserSession.getLoggedInPerson().getEmail());
        Optional<Post> targetPost = getPostService().getPostById(post.getId());

        Bidding lastBidding = (Bidding) getMaxBiddingOnPost(targetPost.get()).getBody();
        Double newPrice = lastBidding.getPrice() + targetPost.get().getIncrValue();

        Bidding bidding = new Bidding();
        bidding.setPost(targetPost.get());
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
		return biddingRepository.findPostExpirDateLessThanEqualOrderByPrice(LocalDate.now());
	}

    public PostService getPostService() {
        return postService;
    }

    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
