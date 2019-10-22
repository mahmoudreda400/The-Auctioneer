package edu.mum.cs.auctioneer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.cs.auctioneer.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
}
