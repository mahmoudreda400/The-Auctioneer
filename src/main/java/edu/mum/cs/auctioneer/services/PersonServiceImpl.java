package edu.mum.cs.auctioneer.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.auctioneer.models.Person;
import edu.mum.cs.auctioneer.repositories.PersonRepository;

import javax.transaction.Transactional;

@Service
public class PersonServiceImpl implements PersonService{

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
	public Optional<Person> registerNewUserAccount(Person person)  {
//		if (emailExist(person.getEmail())) {
//			throw new EmailExistsException(
//					"There is an account with that email adress: "
//							+  person.getEmail());
//		}
		Optional<Person>person1=Optional.empty();
		try{
		person1= Optional.of(personRepo.save(person));}catch(Exception e){
			e.getStackTrace();
		}
		return person1;
	}
//	private boolean emailExist(String email) {
//		Person person = personRepo.findByEmail(email).get();
//		if (person != null) {
//			return true;
//		}
//		return false;
//	}

}
