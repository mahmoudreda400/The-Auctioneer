package edu.mum.cs.auctioneer.controllers;

import edu.mum.cs.auctioneer.models.Bidding;
import edu.mum.cs.auctioneer.models.Person;
import edu.mum.cs.auctioneer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.mum.cs.auctioneer.services.BiddingService;
import edu.mum.cs.auctioneer.services.UserService;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {

	private UserService userService;
	private BiddingService biddingService;

	@Autowired
	public UserController(UserService userService, BiddingService biddingService) {
		this.userService = userService;
		this.biddingService = biddingService;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody @Valid User person) {
		System.out.println(" >>> resgidter");
		ResponseEntity<String> response = null;
		try {
			Optional<User> personOptional = userService.registerNewUserAccount(person);

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
	
	@GetMapping(value="/getNotifications")
	public List<Bidding> getNotifications(){
		return biddingService.getUserNotificatios(118);
	}
	
}
