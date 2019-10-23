package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Person;
import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.Report;
import edu.mum.cs.auctioneer.models.User;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    ResponseEntity bidOnPost(Post post);

    ResponseEntity reportUser(Report report);

    List<User> getAllUsers();

    List<User> getBlockedUsers();

    Boolean ignoreReports(Long id);

    Boolean blockUser(Long id);

    Boolean activate(Long id);
    
    User getUserByEmail(String email);

	User getUserById(long id);

	Optional<User> registerNewUserAccount(User user);
	User updateUserData(User user);
}
