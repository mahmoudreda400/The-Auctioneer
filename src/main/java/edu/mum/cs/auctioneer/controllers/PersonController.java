package edu.mum.cs.auctioneer.controllers;

import edu.mum.cs.auctioneer.jwt.JwtTokenUtil;
import edu.mum.cs.auctioneer.models.Person;
import edu.mum.cs.auctioneer.services.JwtUserDetailsServiceImpl;
import edu.mum.cs.auctioneer.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
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
                UserDetails userDetails = userDetailsService.loadUserByUsername(person.getEmail());
                String token = jwtTokenUtil.generateToken(userDetails);

                response.put("message", "success");
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
    public Person getProfileData(@RequestHeader("Authorization") String token) {
        Person person = null;
        try {
            String email = jwtTokenUtil.getEmailFromToken(token);
            person = personService.getPersonByEmail(email).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return person;
    }

    @PostMapping(value = "/updateProfile")
    public Person getProfileData(@RequestHeader("Authorization") String token, @RequestBody Person personTpUpdate) {
        Person person = null;
        try {
            String email = jwtTokenUtil.getEmailFromToken(token);
            person = personService.getPersonByEmail(email).get();
            if (person != null) {
                person = personService.updatePersonData(personTpUpdate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return person;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Map<String, String> register(@RequestBody @Valid Person person) {
        Map<String, String> response = new HashMap<String, String>();
        try {
            Optional<Person> personOptional = personService.registerNewUserAccount(person);

            if (!personOptional.isEmpty()) {


                response.put("message", "success");
                response.put("type", person.getRole().toString());
            } else {
                response.put("message", "There is an account with that email adress");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", e.getLocalizedMessage());
        }
        return response;
    }

    }


