package com.ftn.sbnz.model.models;

import java.util.List;

import javax.persistence.Entity;

public class ComplexSymptom {
	private Long id;

	private String name;

	private List<Symptom> symptoms;

	public ComplexSymptom() {
		super();
	}

	public ComplexSymptom(Long id, String name, List<Symptom> symptoms) {
		super();
		this.id = id;
		this.name = name;
		this.symptoms = symptoms;
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
		return "ComplexSymptom [id=" + id + ", name=" + name + ", symptoms=" + symptoms + "]";
	}

}
