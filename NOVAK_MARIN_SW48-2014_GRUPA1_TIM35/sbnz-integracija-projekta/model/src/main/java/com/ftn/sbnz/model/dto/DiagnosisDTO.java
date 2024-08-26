package com.ftn.sbnz.model.dto;

import java.util.List;

import com.ftn.sbnz.model.models.Patient;
import com.ftn.sbnz.model.models.Symptom;
import com.ftn.sbnz.model.models.Diagnosis;
import com.ftn.sbnz.model.models.MentalIllness;

public class DiagnosisDTO {
	private Long id;
	private String code;
	private Patient patient;
	private List<Symptom> symptoms;
	private MentalIllness mentalIllness;

	public DiagnosisDTO() {
		super();
	}

	public DiagnosisDTO(Long id, String code, Patient patient, List<Symptom> symptoms, MentalIllness mentalIllness) {
		super();
		this.id = id;
		this.code = code;
		this.patient = patient;
		this.symptoms = symptoms;
		this.mentalIllness = mentalIllness;
	}

	public DiagnosisDTO(Diagnosis diagnosis) {
		if (diagnosis.getId() != null)
			this.id = diagnosis.getId();
		if (diagnosis.getCode() != null)
			this.code = diagnosis.getCode();
		if (diagnosis.getPatient() != null)
			this.patient = diagnosis.getPatient();
//	    this.symptoms = new ArrayList<Symptom>();
//	    if (diagnosis.getSymptoms() != null)
//	        for (Symptom item : diagnosis.getSymptoms())
//	            this.symptoms.add(new SymptomDTO(item));
//	    if (diagnosis.getMentalIllness() != null)
//	        this.mentalIllness = diagnosis.getMentalIllness();
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
		return "DiagnosisDTO{" + "id=" + id + ", code='" + code + '\'' + ", patient=" + patient + ", symptoms="
				+ symptoms + ", mentalIllness=" + mentalIllness + '}';
	}
}
