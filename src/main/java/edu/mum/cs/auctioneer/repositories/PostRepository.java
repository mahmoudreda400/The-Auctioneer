package edu.mum.cs.auctioneer.repositories;

import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post>getPostById(long Id);
    List<Post> findAllByUser(User user);

}
