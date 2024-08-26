package com.ftn.sbnz.model.dto;

import java.util.Date;
import java.util.List;

import com.ftn.sbnz.model.models.Diagnosis;
import com.ftn.sbnz.model.models.Patient;
import com.ftn.sbnz.model.models.Symptom;
import com.ftn.sbnz.model.models.Therapy;

public class PatientDTO {
	private Long id;
	private String healthCardId;
	private String firstName;
	private String lastName;
	private Date dob;
	private List<Diagnosis> previousDiagnosis;
	private List<Therapy> previousTherapies;
	private List<Diagnosis> currentDiagnosis;
	private List<Therapy> currentTherapies;
	private List<Symptom> previousSymptoms;
	private List<Symptom> currentSymptoms;

	public PatientDTO() {
		super();
	}

	public PatientDTO(Long id, String healthCardId, String firstName, String lastName, Date dob,
			List<Diagnosis> previousDiagnosis, List<Therapy> previousTherapies, List<Diagnosis> currentDiagnosis,
			List<Therapy> currentTherapies, List<Symptom> previousSymptoms, List<Symptom> currentSymptoms) {
		super();
		this.id = id;
		this.healthCardId = healthCardId;
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

	public PatientDTO(Patient patient) {
		if (patient.getId() != null)
			this.id = patient.getId();
		if (patient.getHealthCardId() != null)
			this.healthCardId = patient.getHealthCardId();
		if (patient.getFirstName() != null)
			this.firstName = patient.getFirstName();
		if (patient.getLastName() != null)
			this.lastName = patient.getLastName();
		if (patient.getDob() != null)
			this.dob = patient.getDob();
		// OSTALO ZA DODATI
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHealthCardId() {
		return healthCardId;
	}

	public void setHealthCardId(String healthCardId) {
		this.healthCardId = healthCardId;
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
		return "PatientDTO{" + "id=" + id + ", healthCardId='" + healthCardId + '\'' + ", firstName='" + firstName
				+ '\'' + ", lastName='" + lastName + '\'' + ", dob=" + dob + ", previousDiagnosis=" + previousDiagnosis
				+ ", previousTherapies=" + previousTherapies + ", currentDiagnosis=" + currentDiagnosis
				+ ", currentTherapies=" + currentTherapies + ", previousSymptoms=" + previousSymptoms
				+ ", currentSymptoms=" + currentSymptoms + '}';
	}
}
