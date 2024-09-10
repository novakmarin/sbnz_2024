package com.ftn.sbnz.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.model.models.Appointment;
import com.ftn.sbnz.model.models.Patient;
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
	
	@PutMapping("/suggestTherapies/{id}")
    public ResponseEntity<List<Therapy>> suggestTherapies(@PathVariable Long id, @RequestBody Appointment appointment) {
        System.out.println("USLO U THERAPY CONTROLLER.");
		List<Therapy> suggestedTherapies = therapyService.suggestTherapies(appointment);
        return suggestedTherapies != null ? new ResponseEntity<>(suggestedTherapies, HttpStatus.OK) 
                                      : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
	
	@PutMapping("/updateTherapy/{id}")
    public ResponseEntity<Therapy> updateTherapy(@PathVariable Long id, @RequestBody Therapy therapy) {
        System.out.println("USLO U THERAPY UPDATE.");
		Therapy updatedTherapy = therapyService.updateTherapy(therapy);
        return updatedTherapy != null ? new ResponseEntity<>(updatedTherapy, HttpStatus.OK) 
                                      : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
