package com.ftn.sbnz.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.sbnz.model.models.NewRuleTemplateModel;
import com.ftn.sbnz.model.models.Patient;
import com.ftn.sbnz.service.services.NewRuleTemplateModelService;

@RestController
@RequestMapping("/ruleTemplateModels")
public class NewRuleTemplateModelController {
	
	@Autowired
	private NewRuleTemplateModelService nrtmService;
	
	@PostMapping
    public ResponseEntity<NewRuleTemplateModel> createNrtm(@RequestBody NewRuleTemplateModel nrtm) {
    	if(nrtmService.getNrtmBySymptomName(nrtm.getSymptomName()) == null) {
    		NewRuleTemplateModel savedNrtm = nrtmService.createNrtm(nrtm);
            return new ResponseEntity<>(savedNrtm, HttpStatus.CREATED);
    	}else {
    		System.out.println("NRTM with given HCID already exists.");
    		return new ResponseEntity<NewRuleTemplateModel>(HttpStatus.CONFLICT);
    	}
        
    }

}
