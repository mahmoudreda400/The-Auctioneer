package edu.mum.cs.auctioneer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.mum.cs.auctioneer.jwt.JwtUserFactory;
import edu.mum.cs.auctioneer.models.Person;


/**
 * Created by stephan on 20.03.16.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PersonService personService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Person user = personService.getPersonByEmail(email).get();

		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
		} else {
			return JwtUserFactory.create(user);
		}
	}

}
