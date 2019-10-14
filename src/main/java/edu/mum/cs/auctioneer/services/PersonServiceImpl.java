package edu.mum.cs.auctioneer.services;

import edu.mum.cs.auctioneer.models.Person;
import edu.mum.cs.auctioneer.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

	private PersonRepository personRepo;
	
	@Autowired
	public PersonServiceImpl(PersonRepository personRepo) {
		this.personRepo = personRepo;
	}
	
	@Override
	public Optional<Person> getPersonById(long id) {
		return personRepo.findById(id);
	}

	@Override
	public Optional<Person> getPersonByEmail(String email) {
		return personRepo.findByEmail(email);
	}

	@Override
	public Optional<Person> getPersonByEmailAndPassword(String email, String password) {
		return personRepo.findByEmailAndPassword(email, password);
	}

	@Override
	public Person updatePersonData(Person person) {
		return personRepo.save(person);
	}


    @Transactional
	@Override
	public Optional<Person> registerNewUserAccount(Person person) {
		Optional<Person>result=Optional.empty();;
		try {
			result=Optional.of(personRepo.save(person));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}



}
