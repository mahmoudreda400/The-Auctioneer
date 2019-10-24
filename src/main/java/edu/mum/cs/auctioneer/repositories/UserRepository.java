package edu.mum.cs.auctioneer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.cs.auctioneer.models.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByEmail(String email);

    List<User> findAllByBlocked(Boolean blocked);

    List<User> findAllByBlockedTrue();
    
    @Query("SELECT u FROM User u INNER JOIN u.biddings b")
	List<User> getAllUserHasBidding();
}
