package edu.mum.cs.auctioneer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.auctioneer.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepo;

	@Autowired
	public UserServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
}
