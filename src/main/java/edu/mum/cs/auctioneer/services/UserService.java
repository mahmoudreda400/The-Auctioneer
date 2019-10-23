package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.Report;
import edu.mum.cs.auctioneer.models.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

//    ResponseEntity bidOnPost(Post post);

    ResponseEntity reportUser(String msg, User reported);

    User findByEmail(String email);
}
