package edu.mum.cs.auctioneer.controllers;

import edu.mum.cs.auctioneer.jwt.JwtTokenUtil;
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
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {

	private UserService userService;
	private BiddingService biddingService;
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	public UserController(UserService userService, BiddingService biddingService,JwtTokenUtil jwtTokenUtil) {
		this.userService = userService;
		this.biddingService = biddingService;
		this.jwtTokenUtil = jwtTokenUtil;
	}

	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public List<User> fetchReports() {
		return userService.getAllUsers();
	}

	@RequestMapping(value = "/blocked", method = RequestMethod.GET)
	public List<User> blockedUsers() {
		return userService.getBlockedUsers();
	}

	@RequestMapping(value = "/activate", method = RequestMethod.POST)
	public Boolean activate(Long userId) {
		return userService.activate(userId);
	}

	@RequestMapping(value = "/ignoreReports", method = RequestMethod.POST)
	public Boolean ignoreReports(Long userId) {
		return userService.ignoreReports(userId);
	}

    @RequestMapping(value = "/blockUser", method = RequestMethod.POST)
    public Boolean blockUser(Long userId) {
        return userService.blockUser(userId);
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
	public ResponseEntity getNotifications(@RequestHeader("Authorization") String token){
		ResponseEntity<Object> response = null;
		try {
			String email = jwtTokenUtil.getEmailFromToken(token);
			User user = userService.getUserByEmail(email);
			if(user != null) {
				List<Bidding> bids =  biddingService.getUserNotificatios(user.getId());
				response = new ResponseEntity<Object>(bids, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<Object>(e.getMessage(), HttpStatus.FORBIDDEN);
		}
		return response;
	}
	
	@PostMapping("/reportUser")
	public ResponseEntity reportUser(@RequestParam("msg") String msg, @RequestPart("reported") User user/*, @RequestHeader("Authorization") String token*/){
		return getUserService().reportUser(msg,user);
	}

	//-----------------setters and getters-------------------

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
