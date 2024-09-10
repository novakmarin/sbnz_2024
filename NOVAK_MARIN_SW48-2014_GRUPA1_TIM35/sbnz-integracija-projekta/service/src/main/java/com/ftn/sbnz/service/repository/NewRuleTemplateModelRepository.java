package com.ftn.sbnz.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.sbnz.model.models.NewRuleTemplateModel;
import com.ftn.sbnz.model.models.Symptom;

@Repository
public interface NewRuleTemplateModelRepository extends JpaRepository<NewRuleTemplateModel, Long>{
	
	NewRuleTemplateModel findBySymptomName(String symptomName);
	
}
