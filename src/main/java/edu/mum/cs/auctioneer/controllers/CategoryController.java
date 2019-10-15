package edu.mum.cs.auctioneer.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.cs.auctioneer.jwt.JwtTokenUtil;
import edu.mum.cs.auctioneer.models.Category;
import edu.mum.cs.auctioneer.models.Person;
import edu.mum.cs.auctioneer.models.PersonType;
import edu.mum.cs.auctioneer.services.CategoryService;
import edu.mum.cs.auctioneer.services.JwtUserDetailsServiceImpl;
import edu.mum.cs.auctioneer.services.PersonService;

@RestController
public class CategoryController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PersonService personService;

	@GetMapping(value = "/getAllCategories")
	public ResponseEntity<Object> getAllCategories() {
		try {
			List<Category> c = categoryService.getAllCategories();
			return new ResponseEntity<Object>(c, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.OK);
		}
	}

	@PostMapping(value = "/addCategory")
	public ResponseEntity<Object> addCategory(@RequestHeader("Authorization") String token,
			@RequestBody Category category) {
		String email = jwtTokenUtil.getEmailFromToken(token);
		Optional<Person> op = personService.getPersonByEmail(email);
		if (op.isPresent() && op.get().getRole() == PersonType.admin) {

			return categoryService.addCategory(category);
			// see note 1
//	        return ResponseEntity
//	            .status(HttpStatus.OK)                 
//	            .body(categoryService.addCategory(category));

//			return new ResponseEntity<Object>(c, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(" you don't have permision", HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping(value = "/deleteCategory/{id}")
	public ResponseEntity<String> deleteCategory(@RequestHeader("Authorization") String token, @PathVariable("id") long id){
		String email = jwtTokenUtil.getEmailFromToken(token);
		Optional<Person> op = personService.getPersonByEmail(email);
		if (op.isPresent() && op.get().getRole() == PersonType.admin) {
			return categoryService.deleteCategoryById(id);
		} else {
			return new ResponseEntity<String>(" you don't have permision", HttpStatus.FORBIDDEN);
		}
	}
}
