package edu.mum.cs.auctioneer.services;

import java.util.Optional;

import edu.mum.cs.auctioneer.models.Person;

public interface PersonService {

	Optional<Person> getPersonById(long id);
	
	Optional<Person> getPersonByEmail(String email);
	
	Optional<Person> getPersonByEmailAndPassword(String email,String password);
	
	Person updatePersonData(Person person);
}
