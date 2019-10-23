package edu.mum.cs.auctioneer.controllers;

import edu.mum.cs.auctioneer.models.Bidding;
import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.services.BiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/bidding")
public class BiddingController {

    private BiddingService biddingService;

    @Autowired
    public BiddingController(BiddingService biddingService) {
        this.biddingService = biddingService;
    }

    @PostMapping("/max-bidding")
    public ResponseEntity<Bidding> getMaxBiddingOnPost(@RequestBody Post post){
        return getBiddingService().getMaxBiddingOnPost(post);
    }

    @PostMapping("/bid")
    public ResponseEntity<Bidding> bidOnPost(@RequestBody Post post, @RequestHeader("Authorization") String token){
        //TODO check token validation
        return getBiddingService().bid(post);
    }

    //---------------------setters and getters-----------------


    public BiddingService getBiddingService() {
        return biddingService;
    }

    public void setBiddingService(BiddingService biddingService) {
        this.biddingService = biddingService;
    }
}
