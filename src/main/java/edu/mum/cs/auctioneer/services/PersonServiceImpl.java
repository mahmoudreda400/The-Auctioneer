package edu.mum.cs.auctioneer.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.auctioneer.models.Person;
import edu.mum.cs.auctioneer.repositories.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonRepository personRepo;
	
	@Override
	public Optional<Person> getPersonById(long id) {
		return personRepo.findById(id);
	}

	@Override
	public Optional<Person> getPersonByEmail(String email) {
		return personRepo.findByEmail(email);
	}

}
