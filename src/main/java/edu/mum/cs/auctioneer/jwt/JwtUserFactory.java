package edu.mum.cs.auctioneer.jwt;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;

import edu.mum.cs.auctioneer.models.Person;

public final class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static Person create(Person person) {

		return new Person(person.getId(), person.getName(), person.getEmail(), person.getPassword(), person.getPhone(),
				person.getAddress(), person.getRole());
//		return new org.springframework.security.core.userdetails.User(person.getEmail(), person.getPassword(),
//				new ArrayList<>());

	}
}
