package edu.mum.cs.auctioneer.repositories;

import edu.mum.cs.auctioneer.models.Post;
import edu.mum.cs.auctioneer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post>getPostById(long Id);
    List<Post> findAllByUser(User user);
    
    
    @Query("SELECT p FROM Post p WHERE p.expirDate <= :expirDate")
    List<Post> getAllExpired(@Param("expirDate") LocalDate expirDate);

}
