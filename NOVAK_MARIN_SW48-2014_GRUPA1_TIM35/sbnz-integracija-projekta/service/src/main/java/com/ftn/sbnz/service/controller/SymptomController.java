package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.models.Patient;
import com.ftn.sbnz.model.models.Symptom;
import com.ftn.sbnz.service.services.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/symptoms")
public class SymptomController {
	private final SymptomService symptomService;

    @Autowired
    public SymptomController(SymptomService symptomService) {
        this.symptomService = symptomService;
    }

    // Create a new symptom
    @PostMapping
    public ResponseEntity<Symptom> createSymptom(@RequestBody Symptom symptom) {
    	if(symptomService.findSymptomByName(symptom.getName()) == null) {
    		Symptom savedSymptom = symptomService.saveSymptom(symptom);
            return new ResponseEntity<>(savedSymptom, HttpStatus.CREATED);
    	}else {
    		System.out.println("Symptom with given name already exists.");
    		return new ResponseEntity<Symptom>(HttpStatus.CONFLICT);
    	}
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Symptom> getSymptomById(@PathVariable Long id) {
        Optional<Symptom> symptom = symptomService.findSymptomById(id);
        return symptom.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Symptom> getSymptomByName(@PathVariable String name) {
        Symptom symptom = symptomService.findSymptomByName(name);
        return ResponseEntity.ok(symptom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSymptom(@PathVariable Long id) {
        symptomService.deleteSymptom(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/children")
    public ResponseEntity<List<Symptom>> getAllChildSymptomsBySymptomId(@PathVariable Long id) {
        List<Symptom> childSymptoms = symptomService.findAllChildSymptomsBySymptomId(id);
        return ResponseEntity.ok(childSymptoms);
    }

    @GetMapping
    public ResponseEntity<List<Symptom>> getAllSymptoms() {
        List<Symptom> symptoms = symptomService.findAllSymptoms();
        for(Symptom s: symptoms) {
        	System.out.println(s.getName());
        }
        return ResponseEntity.ok(symptoms);
    }
}
