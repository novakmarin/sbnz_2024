package com.ftn.sbnz.model.dto;

import com.ftn.sbnz.model.models.Therapy;

public class TherapyDTO {
	private Long id;
	private boolean isMedication;
	private String name;

	public TherapyDTO() {
		super();
	}

	public TherapyDTO(Long id, boolean isMedication, String name) {
		super();
		this.id = id;
		this.isMedication = isMedication;
		this.name = name;
	}

	public TherapyDTO(Therapy therapy) {
		if (therapy.getId() != null)
			this.id = therapy.getId();
		this.isMedication = therapy.isMedication();
		if (therapy.getName() != null)
			this.name = therapy.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean getIsmedication() {
		return isMedication;
	}

	public void setIsmedication(boolean isMedication) {
		this.isMedication = isMedication;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TherapyDTO{" + "id=" + id + ", isMedication=" + isMedication + ", name='" + name + '\'' + '}';
	}
}
