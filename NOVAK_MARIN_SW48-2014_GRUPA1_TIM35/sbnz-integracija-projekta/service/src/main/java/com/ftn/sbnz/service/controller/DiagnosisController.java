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

import com.ftn.sbnz.model.models.Diagnosis;
import com.ftn.sbnz.model.models.Patient;
import com.ftn.sbnz.model.models.Symptom;
import com.ftn.sbnz.service.services.DiagnosisService;
import com.ftn.sbnz.service.services.SymptomService;
import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

@RestController
@RequestMapping("/api")
public class DiagnosisController {
	
	@Autowired
	DiagnosisService diagnosisService;
	
	@Autowired
	SymptomService symptomService;
	
	@RequestMapping(value = "/test")
	@PermitAll
	public ResponseEntity<ArrayList<Symptom>> test(HttpServletRequest request){
//		Diagnosis diagnosis = new Diagnosis();
//		Symptom symptom1 = new Symptom(null, "Nedostatak lične higijene", false, null);
//		Symptom symptom2 = new Symptom(null, "Poteškoće sa ustajanjem iz kreveta", false, null);
//		Symptom symptom3 = new Symptom(null, "Insomnija", false, null);
//		Symptom symptom4 = new Symptom(null, "Hipersomnija", false, null);
//		Symptom symptom5 = new Symptom(null, "Izbjegavanje socijalnih situacija", false, null);
//		Symptom symptom6 = new Symptom(null, "Znojenje", false, null);
//		Symptom symptom7 = new Symptom(null, "Crvenilo u licu", false, null);
//		Symptom symptom8 = new Symptom(null, "Drhtanje", false, null);
//		Symptom symptom9 = new Symptom(null, "Vizuelne halucinacije", false, null);
//		Symptom symptom10 = new Symptom(null, "Auditorne halucinacije", false, null);
//		Symptom symptom11 = new Symptom(null, "Alogija", false, null);
//		Symptom symptom12 = new Symptom(null, "Paralogizmi", false, null);
//		Symptom symptom13 = new Symptom(null, "Eholalija", false, null);
//		Symptom symptom14 = new Symptom(null, "Inkoherentnost govora", false, null);
//		Symptom symptom15 = new Symptom(null, "Osjećaj težine u tijelu", false, null);
//		Symptom symptom16 = new Symptom(null, "Tromost", false, null);
//		Symptom symptom17 = new Symptom(null, "Ubrzan rad srca", false, null);
//		Symptom symptom18 = new Symptom(null, "Briga", false, null);
//		Symptom symptom19 = new Symptom(null, "Strah", false, null);
//		Symptom symptom20 = new Symptom(null, "Uznemirenost", false, null);
//		Symptom symptom21 = new Symptom(null, "Smanjen apetit", false, null);
//		Symptom symptom22 = new Symptom(null, "Nedostatak motivacije", false, null);
//		Symptom symptom23 = new Symptom(null, "Suicidalnost", false, null);
//		Symptom symptom24 = new Symptom(null, "Smanjena koncentracija", false, null);
//		Symptom symptom25 = new Symptom(null, "Pove", false, null);
//		
//		
//		Symptom complexSymptom1 = new Symptom(null, "Manjak volje", false, new ArrayList<Symptom>());
//		complexSymptom1.getChildSymptoms().add(symptom1);
//		complexSymptom1.getChildSymptoms().add(symptom2);
//		complexSymptom1.getChildSymptoms().add(symptom16);
//		complexSymptom1.getChildSymptoms().add(symptom21);
//		complexSymptom1.getChildSymptoms().add(symptom22);
//
//		Symptom complexSymptom2 = new Symptom(null, "Halucinacije", false, new ArrayList<Symptom>());
//		complexSymptom2.getChildSymptoms().add(symptom9);
//		complexSymptom2.getChildSymptoms().add(symptom10);
//		
//		Symptom complexSymptom3 = new Symptom(null, "Strah od socijalnih situacija", false, new ArrayList<Symptom>());
//		complexSymptom3.getChildSymptoms().add(symptom5);
//		complexSymptom3.getChildSymptoms().add(symptom6);
//		complexSymptom3.getChildSymptoms().add(symptom7);
//		complexSymptom3.getChildSymptoms().add(symptom17);
//		
//		Symptom complexSymptom4 = new Symptom(null, "Problemi sa snom", false, new ArrayList<Symptom>());
//		complexSymptom4.getChildSymptoms().add(symptom3);
//		complexSymptom4.getChildSymptoms().add(symptom4);
//		
//		Symptom complexSymptom5 = new Symptom(null, "Anksioznost", false, new ArrayList<Symptom>());
//		complexSymptom5.getChildSymptoms().add(symptom18);
//		complexSymptom5.getChildSymptoms().add(symptom19);
//		complexSymptom5.getChildSymptoms().add(symptom20);
//		
//		Symptom complexSymptom6 = new Symptom(null, "Manjak energije", false, new ArrayList<Symptom>());
//		complexSymptom6.getChildSymptoms().add(symptom2);
//		complexSymptom6.getChildSymptoms().add(symptom4);
//		complexSymptom6.getChildSymptoms().add(symptom15);
//		complexSymptom6.getChildSymptoms().add(symptom16);
//		
//		Symptom mentalIllness1 = new Symptom(null, "Socijalna anksioznost", true, new ArrayList<Symptom>());
//		mentalIllness1.getChildSymptoms().add(complexSymptom3);
//		mentalIllness1.getChildSymptoms().add(complexSymptom5);
//
//		Symptom mentalIllness2 = new Symptom(null, "Depresivni poremećaj", true, new ArrayList<Symptom>());
//		mentalIllness2.getChildSymptoms().add(complexSymptom1);
//		mentalIllness2.getChildSymptoms().add(complexSymptom6);
//		mentalIllness2.getChildSymptoms().add(complexSymptom4);
//		mentalIllness2.getChildSymptoms().add(symptom23);
//		mentalIllness2.getChildSymptoms().add(symptom24);
//











		
		
		
		
		
		
		ArrayList<Symptom> allSymptoms = (ArrayList<Symptom>) symptomService.findAllSymptoms();
		int i = 0;
		for(Symptom s: allSymptoms) {
			if(s.getChildSymptoms() != null) {
				System.out.println(i +  " " + s.getChildSymptoms().size());
			}else {
				System.out.println(i + " " + 0);
			}
			i++;
		}
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
		
		System.out.println("Radi controller!");
		return new ResponseEntity<ArrayList<Symptom>>(allSymptoms, HttpStatus.CREATED);
	}
	
	
}
