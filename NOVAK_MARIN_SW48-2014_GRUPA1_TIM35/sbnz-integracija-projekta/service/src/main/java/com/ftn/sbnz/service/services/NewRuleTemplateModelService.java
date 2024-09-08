package com.ftn.sbnz.service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.sbnz.model.models.NewRuleTemplateModel;
import com.ftn.sbnz.model.models.Patient;
import com.ftn.sbnz.service.repository.NewRuleTemplateModelRepository;

@Service
@Transactional
public class NewRuleTemplateModelService {

	@Autowired
	NewRuleTemplateModelRepository nrtmRepository;
	
	public NewRuleTemplateModel createNrtm(NewRuleTemplateModel nrtm) {
        return nrtmRepository.save(nrtm);
    }
	
	public NewRuleTemplateModel getNrtmBySymptomName(String symptomName) {
        return nrtmRepository.findBySymptomName(symptomName);
    }
	
	public List<NewRuleTemplateModel> getAll(){
		return nrtmRepository.findAll();
	}
}
