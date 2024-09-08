package com.ftn.sbnz.model.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "newRuleTemplate")
public class NewRuleTemplateModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "symptomName", nullable = false, unique = true)
	private String symptomName;
	
	@Column(name = "minSymptomsPresent", nullable = false, unique = false)
	private int minSymptomsPresent;

	public NewRuleTemplateModel() {
		super();
	}

	public NewRuleTemplateModel(Long id, String symptomName, int minSymptomsPresent) {
		super();
		this.id = id;
		this.symptomName = symptomName;
		this.minSymptomsPresent = minSymptomsPresent;
	}

	public String getSymptomName() {
		return symptomName;
	}

	public void setSymptomName(String symptomName) {
		this.symptomName = symptomName;
	}

	public int getMinSymptomsPresent() {
		return minSymptomsPresent;
	}

	public void setMinSymptomsPresent(int minSymptomsPresent) {
		this.minSymptomsPresent = minSymptomsPresent;
	}
}
