package com.ftn.sbnz.model.models;

import java.util.List;
import java.util.Objects;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "symptom")
public class Symptom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	private boolean isAMentalIllness;
	
	private boolean hasSpecialDiagnostics;

	//@OneToMany(cascade = CascadeType.ALL)
	//@JoinColumn(name = "parent_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "symptom_relationship",
        joinColumns = @JoinColumn(name = "parent_symptom_id"),
        inverseJoinColumns = @JoinColumn(name = "child_symptom_id")
    )
	private List<Symptom> childSymptoms;

	public Symptom() {
		super();
	}

	public Symptom(Long id, String name, boolean isAMentalIllness, boolean hasSpecialDiagnostics, List<Symptom> childSymptoms) {
		super();
		this.id = id;
		this.name = name;
		this.isAMentalIllness = isAMentalIllness;
		this.hasSpecialDiagnostics = hasSpecialDiagnostics;
		this.childSymptoms = childSymptoms;
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

	public boolean isAMentalIllness() {
		return isAMentalIllness;
	}

	public void setAMentalIllness(boolean isAMentalIllness) {
		this.isAMentalIllness = isAMentalIllness;
	}

	public List<Symptom> getChildSymptoms() {
		return childSymptoms;
	}

	public void setChildSymptoms(List<Symptom> childSymptoms) {
		this.childSymptoms = childSymptoms;
	}
	
	

	public boolean isHasSpecialDiagnostics() {
		return hasSpecialDiagnostics;
	}

	public void setHasSpecialDiagnostics(boolean hasSpecialDiagnostics) {
		this.hasSpecialDiagnostics = hasSpecialDiagnostics;
	}

	@Override
	public String toString() {
		return "Symptom [id=" + id + ", name=" + name + ", childSymptoms=" + childSymptoms + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(childSymptoms, id, isAMentalIllness, hasSpecialDiagnostics, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Symptom other = (Symptom) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
	

}