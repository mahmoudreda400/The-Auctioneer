package edu.mum.cs.auctioneer.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import edu.mum.cs.auctioneer.session.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.cs.auctioneer.jwt.JwtTokenUtil;
import edu.mum.cs.auctioneer.models.Person;
import edu.mum.cs.auctioneer.services.JwtUserDetailsServiceImpl;
import edu.mum.cs.auctioneer.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class PersonController {

	@Autowired
	private JwtUserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, String> login(@RequestParam("email") String email, @RequestParam("password") String password) {
		Map<String, String> response = new HashMap<String, String>();
		try {
			Optional<Person> personOptional = personService.getPersonByEmailAndPassword(email, password);

			if (!personOptional.isEmpty()) {
				Person person = personOptional.get();

				//put looged in user into session
				UserSession.setLoggedInPerson(person);

				UserDetails userDetails = userDetailsService.loadUserByUsername(person.getEmail());
				String token = jwtTokenUtil.generateToken(userDetails);

				response.put("message", "success");
				response.put("name", personOptional.get().getName());
				response.put("token", token);
				response.put("type", person.getRole().toString());
			} else {
				response.put("message", "user name or password is incorrect");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("message", e.getLocalizedMessage());
		}
		return response;
	}

	@GetMapping(value = "/profileData")
	public ResponseEntity<Object> getProfileData(@RequestHeader("Authorization") String token) {
		ResponseEntity<Object> response = null;
		try {
			String email = jwtTokenUtil.getEmailFromToken(token);
			Person person = personService.getPersonByEmail(email).get();
			response = new ResponseEntity<Object>(person, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<Object>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
		return response;
	}

	@PostMapping(value = "/updateProfile")
	public ResponseEntity<Object> getProfileData(@RequestHeader("Authorization") String token,
			@RequestBody Person personTpUpdate) {
		ResponseEntity<Object> response = null;
		try {
			String email = jwtTokenUtil.getEmailFromToken(token);
			Person person = personService.getPersonByEmail(email).get();
			if (person != null) {
				person = personService.updatePersonData(personTpUpdate);
				response = new ResponseEntity<Object>(person, HttpStatus.OK);
			} else {
				response = new ResponseEntity<Object>("please login first", HttpStatus.FORBIDDEN);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<Object>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
		return response;
	}


}
