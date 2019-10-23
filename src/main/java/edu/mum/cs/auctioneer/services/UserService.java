package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.Report;
import edu.mum.cs.auctioneer.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    ResponseEntity bidOnPost(Post post);

    ResponseEntity reportUser(Report report);

    List<User> getAllUsers();

    Boolean ignoreReports(Long id);
    Boolean blockUser(Long id);
}
