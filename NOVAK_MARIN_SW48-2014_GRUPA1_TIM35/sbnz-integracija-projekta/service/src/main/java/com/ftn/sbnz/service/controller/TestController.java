package com.ftn.sbnz.service.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.model.models.Patient;
import com.ftn.sbnz.model.models.Symptom;
import com.ftn.sbnz.service.services.DiagnosisService;
import com.ftn.sbnz.service.services.PatientService;
import com.ftn.sbnz.service.services.SymptomService;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	DiagnosisService diagnosisService;
	
	@Autowired
	SymptomService symptomService;
	
	@Autowired
	PatientService patientService;
	
	
	@RequestMapping(value = "/addComplex")
	@PermitAll
	public ResponseEntity<Patient> test(HttpServletRequest request){

		ArrayList<String> symptomNames = new ArrayList<String>(Arrays.asList(
				"Izbjegavanje socijalnih situacija",
				"Znojenje",
				"Crvenilo u licu",
				"Ubrzan rad srca"
				));
		
		ArrayList<Symptom> currentSymptoms1 = new ArrayList<Symptom>();
		
		for (String s : symptomNames) {
			Symptom newSymptom = symptomService.findSymptomByName(s);
			currentSymptoms1.add(newSymptom);
		}
		
		Patient patient = new Patient(1L, "QWER123", "Petar", "Petrovic", new Date(1980, 6, 2), null, null, null, null, null, null);
		patient.setCurrentSymptoms(currentSymptoms1);
		patient = patientService.addComplexSymptoms(patient);
		System.out.println(patient);
		return new ResponseEntity<Patient>(patient, HttpStatus.CREATED);
	}
}
