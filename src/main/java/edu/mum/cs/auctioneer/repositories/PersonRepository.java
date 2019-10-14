package edu.mum.cs.auctioneer.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.cs.auctioneer.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	Optional<Person> findByEmail(String email);
}
