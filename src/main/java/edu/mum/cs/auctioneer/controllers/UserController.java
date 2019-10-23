package edu.mum.cs.auctioneer.controllers;

import edu.mum.cs.auctioneer.models.Person;
import edu.mum.cs.auctioneer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.mum.cs.auctioneer.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public List<User> fetchReports() {
		return userService.getAllUsers();
	}

	@RequestMapping(value = "/ignoreReports", method = RequestMethod.POST)
	public Boolean ignoreReports(Long userId) {
		return userService.ignoreReports(userId);
	}

    @RequestMapping(value = "/blockUser", method = RequestMethod.POST)
    public Boolean blockUser(Long userId) {
        return userService.blockUser(userId);
    }


	}
