package edu.mum.cs.auctioneer.controllers;

import edu.mum.cs.auctioneer.models.Person;
import edu.mum.cs.auctioneer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.mum.cs.auctioneer.services.UserService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody @Valid User user) {
		ResponseEntity<String> response = null;
		try {
			Optional<User> personOptional = userService.registerNewUserAccount(user);

			if (!personOptional.isEmpty()) {
				response = new ResponseEntity<String>("success", HttpStatus.OK);
			} else {
				response = new ResponseEntity<String>("There is an account with that email adress",
						HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
		return response;
	}
	
}
