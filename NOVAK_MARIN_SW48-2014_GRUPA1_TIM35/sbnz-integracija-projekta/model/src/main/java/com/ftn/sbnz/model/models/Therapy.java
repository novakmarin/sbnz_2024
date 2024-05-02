package com.ftn.sbnz.model.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
public class Therapy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean isMedication;

    private String name;

	public Therapy() {
		super();
	}

	public Therapy(Long id, boolean isMedication, String name) {
		super();
		this.id = id;
		this.isMedication = isMedication;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isMedication() {
		return isMedication;
	}

	public void setMedication(boolean isMedication) {
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
		return "Therapy [id=" + id + ", isMedication=" + isMedication + ", name=" + name + "]";
	}

}
