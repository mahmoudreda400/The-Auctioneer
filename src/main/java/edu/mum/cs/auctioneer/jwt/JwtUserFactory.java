package edu.mum.cs.auctioneer.jwt;

import edu.mum.cs.auctioneer.models.Person;

public final class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static Person create(Person person) {

		return new Person(person.getId(), person.getName(), person.getEmail(), person.getPassword(), person.getPhone(),
				person.getAddress());

	}
}
