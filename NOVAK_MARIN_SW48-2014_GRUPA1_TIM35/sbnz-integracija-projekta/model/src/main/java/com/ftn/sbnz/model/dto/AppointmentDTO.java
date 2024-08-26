package com.ftn.sbnz.model.dto;

//import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ftn.sbnz.model.models.Appointment;
import com.ftn.sbnz.model.models.Diagnosis;
import com.ftn.sbnz.model.models.Doctor;
import com.ftn.sbnz.model.models.Patient;
import com.ftn.sbnz.model.models.Symptom;

public class AppointmentDTO {
	private Long id;
	private Date date;
	private Doctor doctor;
	private Patient patient;
	private Diagnosis diagnosis;
	private List<Symptom> currentSymptoms;
	private String note;

	public AppointmentDTO() {
		super();
	}

	public AppointmentDTO(Long id, Date date, Doctor doctor, Patient patient, Diagnosis diagnosis,
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

	public AppointmentDTO(Appointment appointment) {
		if (appointment.getId() != null)
			this.id = appointment.getId();
		if (appointment.getDate() != null)
			this.date = appointment.getDate();
		if (appointment.getDoctor() != null)
			this.doctor = appointment.getDoctor();
		if (appointment.getPatient() != null)
			this.patient = appointment.getPatient();
		if (appointment.getDiagnosis() != null)
			this.diagnosis = appointment.getDiagnosis();
		if (appointment.getNote() != null)
			this.note = appointment.getNote();
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

	public Diagnosis getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		this.diagnosis = diagnosis;
	}

	public List<Symptom> getCurrentsymptoms() {
		return currentSymptoms;
	}

	public void setCurrentsymptoms(List<Symptom> currentSymptoms) {
		this.currentSymptoms = currentSymptoms;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "AppointmentDTO{" + "id=" + id + ", date=" + date + ", doctor=" + doctor + ", patient=" + patient
				+ ", diagnosis=" + diagnosis + ", currentSymptoms=" + currentSymptoms + ", note='" + note + '\'' + '}';
	}
}
