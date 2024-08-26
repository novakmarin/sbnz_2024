package com.ftn.sbnz.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.ftn.sbnz.model.models.ComplexSymptom;
import com.ftn.sbnz.model.models.Symptom;

public class ComplexSymptomDTO {
	private Long id;
	private String name;
	private List<Symptom> symptoms;

	public ComplexSymptomDTO() {
		super();
	}

	public ComplexSymptomDTO(Long id, String name, List<Symptom> symptoms) {
		super();
		this.id = id;
		this.name = name;
		this.symptoms = symptoms;
	}

	public ComplexSymptomDTO(ComplexSymptom complexsymptom) {
		if (complexsymptom.getId() != null)
			this.id = complexsymptom.getId();
		if (complexsymptom.getName() != null)
			this.name = complexsymptom.getName();
		this.symptoms = new ArrayList<Symptom>();
		if (complexsymptom.getSymptoms() != null)
			for (Symptom item : complexsymptom.getSymptoms())
				this.symptoms.add(item);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Symptom> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(List<Symptom> symptoms) {
		this.symptoms = symptoms;
	}

	@Override
	public String toString() {
		return "ComplexSymptomDTO{" + "id=" + id + ", name='" + name + '\'' + ", symptoms=" + symptoms + '}';
	}
}
