package com.ftn.sbnz.model.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorValue("MentalIllness")
public class MentalIllness extends Symptom{

	public MentalIllness() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MentalIllness(Long id, String name, List<Symptom> parentSymptoms, List<Symptom> childSymptoms) {
		super(id, name, parentSymptoms, childSymptoms);
		// TODO Auto-generated constructor stub
	}
	
}
