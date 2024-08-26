package com.ftn.sbnz.service.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.model.dto.LoginDTO;
import com.ftn.sbnz.model.models.Doctor;
import com.ftn.sbnz.model.models.User;
import com.ftn.sbnz.service.services.DoctorService;
import com.ftn.sbnz.service.services.UserService;

@RestController
public class AuthenticationController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	DoctorService doctorService;
	
	@PostMapping(value = "/login",consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public  ResponseEntity<User> logIn(@RequestBody LoginDTO loginDTO, HttpServletRequest request){
    	User u=userService.getUserByUsername(loginDTO.getUsername());
		System.out.println("USLO U LOGIN!");
    	if(u==null){
			System.out.println("Korisnik ne postoji");
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
		if(!(u.getPassword().equals(u.getPassword()))){
			System.out.println("Pogresna lozinka");
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
		System.out.println("Login uspjesan");
		Doctor doctor = doctorService.getDoctorByUsername(u.getUsername());
		return new ResponseEntity<User>(u, HttpStatus.OK);	
    }
}
