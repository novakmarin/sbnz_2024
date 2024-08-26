package com.ftn.sbnz.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.ftn.sbnz.model.models.Symptom;

public class SymptomDTO {
	private Long id;
	private String name;
	private boolean isAMentalIllness;
	private boolean hasSpecialDiagnostics;
	private List<SymptomDTO> childSymptoms;

	public SymptomDTO() {
		super();
	}

	public SymptomDTO(Long id, String name, boolean isAMentalIllness, boolean hasSpecialDiagnostics,
			List<SymptomDTO> childSymptoms) {
		super();
		this.id = id;
		this.name = name;
		this.isAMentalIllness = isAMentalIllness;
		this.hasSpecialDiagnostics = hasSpecialDiagnostics;
		this.childSymptoms = childSymptoms;
	}

	public SymptomDTO(Symptom symptom) {
		if (symptom.getId() != null)
			this.id = symptom.getId();
		if (symptom.getName() != null)
			this.name = symptom.getName();
		this.isAMentalIllness = symptom.isAMentalIllness();

		this.hasSpecialDiagnostics = symptom.isHasSpecialDiagnostics();
		this.childSymptoms = new ArrayList<SymptomDTO>();
		if (symptom.getChildSymptoms() != null)
			for (Symptom item : symptom.getChildSymptoms()) {
				SymptomDTO newSymptom = new SymptomDTO(item);
				this.childSymptoms.add(newSymptom);
			}

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

	public boolean getIsAMentalIllness() {
		return isAMentalIllness;
	}

	public void setIsAMentalIllness(boolean isAMentalIllness) {
		this.isAMentalIllness = isAMentalIllness;
	}

	public boolean getHasSpecialDiagnostics() {
		return hasSpecialDiagnostics;
	}

	public void setHasSpecialDiagnostics(boolean hasSpecialDiagnostics) {
		this.hasSpecialDiagnostics = hasSpecialDiagnostics;
	}

	public List<SymptomDTO> getChildSymptoms() {
		return childSymptoms;
	}

	public void setChildSymptoms(List<SymptomDTO> childSymptoms) {
		this.childSymptoms = childSymptoms;
	}

	@Override
	public String toString() {
		return "SymptomDTO{" + "id=" + id + ", name='" + name + '\'' + ", isAMentalIllness=" + isAMentalIllness
				+ ", hasSpecialDiagnostics=" + hasSpecialDiagnostics + ", childSymptoms=" + childSymptoms + '}';
	}
}
