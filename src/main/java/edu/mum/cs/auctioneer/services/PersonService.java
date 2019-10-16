package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Person;
import edu.mum.cs.auctioneer.models.User;

import java.util.Optional;

public interface PersonService {

	Optional<Person> getPersonById(long id);

	Optional<Person> getPersonByEmail(String email);

	Optional<Person> getPersonByEmailAndPassword(String email,String password);

	Person updatePersonData(Person person);

	Optional<Person> registerNewUserAccount(Person person);


}