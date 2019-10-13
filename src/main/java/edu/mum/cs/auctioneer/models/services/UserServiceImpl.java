package edu.mum.cs.auctioneer.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.auctioneer.models.services.repositories.UserRepository;

@Service
public class UserServiceImpl {

	private UserRepository userRepo;

	@Autowired
	public UserServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
}
