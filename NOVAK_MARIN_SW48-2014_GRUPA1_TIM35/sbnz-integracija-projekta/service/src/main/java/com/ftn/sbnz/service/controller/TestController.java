package com.ftn.sbnz.service.controller;

import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.SimpleDateFormat;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.model.models.Appointment;
import com.ftn.sbnz.model.models.Patient;
import com.ftn.sbnz.model.models.Symptom;
import com.ftn.sbnz.service.services.AppointmentService;
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
	
	@Autowired
	AppointmentService appointmentService;
	
	@RequestMapping(value = "/complex")
	@PermitAll
	public ResponseEntity<Patient> testComplex(HttpServletRequest request){

		ArrayList<String> symptomNames = new ArrayList<String>(Arrays.asList(
				"Izbjegavanje socijalnih situacija",
				"Znojenje",
				"Crvenilo u licu",
				"Ubrzan rad srca",
				"Uznemirenost",
				"Strah",
				"Briga"
				));
		
		
		ArrayList<Symptom> currentSymptoms1 = new ArrayList<Symptom>();
		
		ArrayList<Symptom> previousSymptoms1 = new ArrayList<Symptom>();
		
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
	
	@RequestMapping(value = "/manic")
	@PermitAll
	public ResponseEntity<Patient> testManic(HttpServletRequest request){

		ArrayList<String> symptomNames = new ArrayList<String>(Arrays.asList(
				"Grandioznost",
				"Pretjerana razgovorljivost",
				"Nestabilna pažnja",
				"Višak energije",
				"Povećana motivacija"
				));
		
		
		ArrayList<Symptom> currentSymptoms1 = new ArrayList<Symptom>();
		
		ArrayList<Symptom> previousSymptoms1 = new ArrayList<Symptom>();
		
		for (String s : symptomNames) {
			Symptom newSymptom = symptomService.findSymptomByName(s);
			currentSymptoms1.add(newSymptom);
		}
		
		
		Patient patient = new Patient(1L, "QWER120", "Stevan", "Stevanović", new Date(1980, 6, 2), null, null, null, null, null, null);
		patient.setCurrentSymptoms(currentSymptoms1);
		patient = patientService.addComplexSymptoms(patient);
		
		
		System.out.println(patient);
		return new ResponseEntity<Patient>(patient, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/gad")
	@PermitAll
	public ResponseEntity<Patient> testGad(HttpServletRequest request){
		
		ArrayList<String> previousSymptomNames = new ArrayList<String>(Arrays.asList(
				"Anksioznost",
				"Zamor",
				"Smanjena koncentracija",
				"Razdražljivost"
				));
		
		ArrayList<Symptom> previousSymptoms1 = new ArrayList<Symptom>();
		
		
		for (String s : previousSymptomNames) {
			Symptom newSymptom = symptomService.findSymptomByName(s);
			previousSymptoms1.add(newSymptom);
		}
		
		Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1984);
        calendar.set(Calendar.MONTH, Calendar.JUNE);
        calendar.set(Calendar.DAY_OF_MONTH, 2);
        Date dob = calendar.getTime();
		Patient patientGAD = new Patient(null, "QWER122", "Marko", "Markovic", dob, null, null, null, null, null, null);
		patientGAD.setCurrentSymptoms(previousSymptoms1);
		//patientService.createPatient(patientGAD);
		
		
		calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, Calendar.MARCH);
        calendar.set(Calendar.DAY_OF_MONTH, 3);
        Date previousAppointmentDate = calendar.getTime();
		patientGAD = patientService.getPatientByHealthCardId("QWER122");
		
		Appointment previousAppointment = new Appointment(1L, previousAppointmentDate, null, patientGAD, null, previousSymptoms1, null);
		//appointmentService.saveAppointment(previousAppointment);
		
		Appointment currentAppointment = new Appointment(null, Calendar.getInstance().getTime() , null, patientGAD, null, previousSymptoms1, null);
		//System.out.println(patient);
		appointmentService.fireRules(currentAppointment);
		System.out.println(currentAppointment.getPatient());
		return new ResponseEntity<Patient>(currentAppointment.getPatient(), HttpStatus.CREATED);
	}
	
	
	
	
	@RequestMapping(value = "/addComplex")
	@PermitAll
	public ResponseEntity<Patient> test(HttpServletRequest request){

		ArrayList<String> symptomNames = new ArrayList<String>(Arrays.asList(
				"Izbjegavanje socijalnih situacija",
				"Znojenje",
				"Crvenilo u licu",
				"Ubrzan rad srca",
				"Uznemirenost",
				"Strah",
				"Briga"
				));
		
		ArrayList<String> previousSymptomNames = new ArrayList<String>(Arrays.asList(
				"Anksioznost",
				"Zamor",
				"Smanjena koncentracija",
				"Razdražljivost"
				));
		
		ArrayList<Symptom> currentSymptoms1 = new ArrayList<Symptom>();
		
		ArrayList<Symptom> previousSymptoms1 = new ArrayList<Symptom>();
		
		for (String s : symptomNames) {
			Symptom newSymptom = symptomService.findSymptomByName(s);
			currentSymptoms1.add(newSymptom);
		}
		
		for (String s : previousSymptomNames) {
			Symptom newSymptom = symptomService.findSymptomByName(s);
			previousSymptoms1.add(newSymptom);
		}
		
		Patient patient = new Patient(1L, "QWER123", "Petar", "Petrovic", new Date(1980, 6, 2), null, null, null, null, null, null);
		//patient.setCurrentSymptoms(currentSymptoms1);
		//patient = patientService.addComplexSymptoms(patient);
		
		Patient patientGAD = new Patient(null, "QWER122", "Marko", "Markovic", new Date(1984, 6, 2), null, null, null, null, null, null);
		patientGAD.setCurrentSymptoms(previousSymptoms1);
		//patientService.createPatient(patientGAD);
		
		patientGAD = patientService.getPatientByHealthCardId("QWER122");
		
		Appointment previousAppointment = new Appointment(1L, new Date(2023, 3, 3), null, patientGAD, null, previousSymptoms1, null);
		//appointmentService.saveAppointment(previousAppointment);
		
		
		Appointment currentAppointment = new Appointment(null, new Date(2024, 7, 5) , null, patientGAD, null, previousSymptoms1, null);
		//System.out.println(patient);
		appointmentService.fireRules(currentAppointment);
		System.out.println(currentAppointment.getPatient());
		return new ResponseEntity<Patient>(currentAppointment.getPatient(), HttpStatus.CREATED);
	}
}
