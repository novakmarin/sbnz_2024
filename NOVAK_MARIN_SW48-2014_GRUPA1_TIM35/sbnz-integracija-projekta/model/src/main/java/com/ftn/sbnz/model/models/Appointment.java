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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne(fetch = FetchType.LAZY)
	private Doctor doctor;

	@ManyToOne(fetch = FetchType.LAZY)
	private Patient patient;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Diagnosis diagnosis;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "appointment_symptoms", joinColumns = { @JoinColumn(name = "appointment_id") }, // List of
																										// JoinColumn
																										// annotations
			inverseJoinColumns = { @JoinColumn(name = "symptom_id") } // List of JoinColumn annotations
	)
	private List<Symptom> currentSymptoms;

	private String note;

	public Appointment() {
		super();
	}

	public Appointment(Long id, Date date, Doctor doctor, Patient patient, Diagnosis diagnosis,
			List<Symptom> currentSymptoms, String note) {
		super();
		this.id = id;
		this.date = date;
		this.doctor = doctor;
		this.patient = patient;
		this.diagnosis = diagnosis;
		this.currentSymptoms = currentSymptoms;
		this.note = note;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public List<Symptom> getCurrentSymptoms() {
		return currentSymptoms;
	}

	public void setCurrentSymptoms(List<Symptom> currentSymptoms) {
		this.currentSymptoms = currentSymptoms;
	}

	public Diagnosis getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", date=" + date + ", doctor=" + doctor + ", patient=" + patient
				+ ", diagnosis=" + diagnosis + ", currentSymptoms=" + currentSymptoms + ", note=" + note + "]";
	}

}
