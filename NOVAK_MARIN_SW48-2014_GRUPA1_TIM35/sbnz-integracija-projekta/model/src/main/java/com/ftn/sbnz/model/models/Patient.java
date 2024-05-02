package com.ftn.sbnz.model.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	private String lastName;

	@Temporal(TemporalType.DATE)
	private Date dob;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "patient_previous_diagnosis",
        joinColumns = @JoinColumn(name = "patient_id"),
        inverseJoinColumns = @JoinColumn(name = "diagnosis_id")
    )
	private List<Diagnosis> previousDiagnosis;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "patient_previous_therapies",
        joinColumns = @JoinColumn(name = "patient_id"),
        inverseJoinColumns = @JoinColumn(name = "therapy_id")
    )
	private List<Therapy> previousTherapies;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "patient_current_diagnosis",
        joinColumns = @JoinColumn(name = "patient_id"),
        inverseJoinColumns = @JoinColumn(name = "diagnosis_id")
    )
	private List<Diagnosis> currentDiagnosis;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "patient_current_therapies",
        joinColumns = @JoinColumn(name = "patient_id"),
        inverseJoinColumns = @JoinColumn(name = "therapy_id")
    )
	private List<Therapy> currentTherapies;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "patient_previous_symptoms",
        joinColumns = @JoinColumn(name = "patient_id"),
        inverseJoinColumns = @JoinColumn(name = "symptom_id")
    )
	private List<Symptom> previousSymptoms;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "patient_current_symptoms",
        joinColumns = @JoinColumn(name = "patient_id"),
        inverseJoinColumns = @JoinColumn(name = "symptom_id")
    )
	private List<Symptom> currentSymptoms;

	public Patient() {
		super();
	}

	public Patient(Long id, String firstName, String lastName, Date dob, List<Diagnosis> previousDiagnosis,
			List<Therapy> previousTherapies, List<Diagnosis> currentDiagnosis, List<Therapy> currentTherapies,
			List<Symptom> previousSymptoms, List<Symptom> currentSymptoms) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.previousDiagnosis = previousDiagnosis;
		this.previousTherapies = previousTherapies;
		this.currentDiagnosis = currentDiagnosis;
		this.currentTherapies = currentTherapies;
		this.previousSymptoms = previousSymptoms;
		this.currentSymptoms = currentSymptoms;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public List<Diagnosis> getPreviousDiagnosis() {
		return previousDiagnosis;
	}

	public void setPreviousDiagnosis(List<Diagnosis> previousDiagnosis) {
		this.previousDiagnosis = previousDiagnosis;
	}

	public List<Therapy> getPreviousTherapies() {
		return previousTherapies;
	}

	public void setPreviousTherapies(List<Therapy> previousTherapies) {
		this.previousTherapies = previousTherapies;
	}

	public List<Diagnosis> getCurrentDiagnosis() {
		return currentDiagnosis;
	}

	public void setCurrentDiagnosis(List<Diagnosis> currentDiagnosis) {
		this.currentDiagnosis = currentDiagnosis;
	}

	public List<Therapy> getCurrentTherapies() {
		return currentTherapies;
	}

	public void setCurrentTherapies(List<Therapy> currentTherapies) {
		this.currentTherapies = currentTherapies;
	}

	public List<Symptom> getPreviousSymptoms() {
		return previousSymptoms;
	}

	public void setPreviousSymptoms(List<Symptom> previousSymptoms) {
		this.previousSymptoms = previousSymptoms;
	}

	public List<Symptom> getCurrentSymptoms() {
		return currentSymptoms;
	}

	public void setCurrentSymptoms(List<Symptom> currentSymptoms) {
		this.currentSymptoms = currentSymptoms;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", previousDiagnosis=" + previousDiagnosis + ", previousTherapies=" + previousTherapies
				+ ", currentDiagnosis=" + currentDiagnosis + ", currentTherapies=" + currentTherapies
				+ ", previousSymptoms=" + previousSymptoms + ", currentSymptoms=" + currentSymptoms + "]";
	}

}
