package com.ftn.sbnz.model.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "diagnosis_symptoms",
               joinColumns = @JoinColumn(name = "diagnosis_id"),
               inverseJoinColumns = @JoinColumn(name = "symptom_id"))
    private List<Symptom> symptoms;

    @ManyToOne(fetch = FetchType.LAZY)
    private MentalIllness mentalIllness;

	public Diagnosis() {
		super();
	}

	public Diagnosis(Long id, String code, Patient patient, List<Symptom> symptoms, MentalIllness mentalIllness) {
		super();
		this.id = id;
		this.code = code;
		this.patient = patient;
		this.symptoms = symptoms;
		this.mentalIllness = mentalIllness;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<Symptom> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(List<Symptom> symptoms) {
		this.symptoms = symptoms;
	}

	public MentalIllness getMentalIllness() {
		return mentalIllness;
	}

	public void setMentalIllness(MentalIllness mentalIllness) {
		this.mentalIllness = mentalIllness;
	}

	@Override
	public String toString() {
		return "Diagnosis [id=" + id + ", code=" + code + ", patient=" + patient + ", symptoms=" + symptoms
				+ ", mentalIllness=" + mentalIllness + "]";
	}

}
