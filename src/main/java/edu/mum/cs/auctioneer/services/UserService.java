package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.Report;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface UserService {

    ResponseEntity bidOnPost(Post post);

    ResponseEntity reportUser(Report report);
}
