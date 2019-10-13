package edu.mum.cs.auctioneer.models.services.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.cs.auctioneer.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
