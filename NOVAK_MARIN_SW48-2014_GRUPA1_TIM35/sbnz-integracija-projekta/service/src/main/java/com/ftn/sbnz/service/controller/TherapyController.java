package com.ftn.sbnz.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.model.models.Therapy;
import com.ftn.sbnz.service.services.TherapyService;

@RestController
@RequestMapping("/therapies")
public class TherapyController {
	
	@Autowired
	private TherapyService therapyService;
	
	@GetMapping
    public ResponseEntity<List<Therapy>> getAllTherapies() {
        List<Therapy> therapies = therapyService.getAllTherapies();
        return new ResponseEntity<>(therapies, HttpStatus.OK);
    }
}
