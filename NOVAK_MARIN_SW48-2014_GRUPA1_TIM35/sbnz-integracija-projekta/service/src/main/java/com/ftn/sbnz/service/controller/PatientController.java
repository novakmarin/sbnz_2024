package com.ftn.sbnz.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ftn.sbnz.model.models.Patient;
import com.ftn.sbnz.model.models.Symptom;
import com.ftn.sbnz.service.services.PatientService;
import com.ftn.sbnz.service.services.SymptomService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;
    
    @Autowired
    private SymptomService symptomService;

    // Create a new patient
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
    	if(patientService.getPatientByHealthCardId(patient.getHealthCardId()) == null) {
    		Patient savedPatient = patientService.createPatient(patient);
            return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    	}else {
    		System.out.println("Patient with given HCID already exists.");
    		return new ResponseEntity<Patient>(HttpStatus.CONFLICT);
    	}
        
    }

    // Retrieve all patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.findAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // Retrieve a single patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patient>> getPatientById(@PathVariable Long id) {
        Optional<Patient> patient = patientService.getPatientById(id);
        return patient != null ? new ResponseEntity<>(patient, HttpStatus.OK) 
                               : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/getPatientByHcidOrJmbg/{healthCardId}")
    public ResponseEntity<Patient> getPatientByHealthCardId(@PathVariable String healthCardId) {
        Patient patient = patientService.getPatientByHealthCardId(healthCardId);
        System.out.println("USLO 1");
        if (patient != null) {
        	System.out.println("USLO 2");
            return ResponseEntity.ok(patient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update a patient
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        Patient updatedPatient = patientService.updatePatient(id, patient);
        return updatedPatient != null ? new ResponseEntity<>(updatedPatient, HttpStatus.OK) 
                                      : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PutMapping("/updateRecommendations/{id}")
    public ResponseEntity<Patient> updateRecommendations(@PathVariable Long id, @RequestBody Patient patient) {
//    	Patient updatedPatient = patient;
//        updatedPatient.setCurrentSymptoms(new ArrayList<Symptom>());
//        for(Symptom s: patient.getCurrentSymptoms()) {
//        	updatedPatient.getCurrentSymptoms().add(s);
//        }
        Patient updatedPatient = patientService.addComplexSymptoms(patient);
        return updatedPatient != null ? new ResponseEntity<>(updatedPatient, HttpStatus.OK) 
                                      : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/getPatientsWithSymptom/{symptomName}")
	public ResponseEntity<List<Patient>> testBackwards(@PathVariable String symptomName){
		Symptom symptom = symptomService.findSymptomByName(symptomName);		
		//System.out.println(symptom);
		List<Patient> patients = patientService.findPatientsWithSymptom(symptom);
		return new ResponseEntity<List<Patient>>(patients, HttpStatus.CREATED);
	}

    // Delete a patient
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

