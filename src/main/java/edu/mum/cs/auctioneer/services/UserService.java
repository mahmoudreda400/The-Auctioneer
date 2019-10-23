package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Person;
import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.Report;
import edu.mum.cs.auctioneer.models.User;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface UserService {

    ResponseEntity bidOnPost(Post post);

    ResponseEntity reportUser(Report report);
    
    User getUserByEmail(String email);

	User getUserById(long id);

	Optional<User> registerNewUserAccount(User user);
	User updateUserData(User user);
}
