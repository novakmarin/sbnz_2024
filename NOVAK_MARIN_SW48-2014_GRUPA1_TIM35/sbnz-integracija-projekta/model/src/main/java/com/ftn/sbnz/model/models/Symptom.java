package com.ftn.sbnz.model.models;

import java.util.List;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@Entity
public class Symptom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
        name = "symptom_relations",
        joinColumns = @JoinColumn(name = "symptom_id"),
        inverseJoinColumns = @JoinColumn(name = "parent_symptom_id")
    )
    private List<Symptom> parentSymptoms;

    @ManyToMany(mappedBy = "parentSymptoms", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Symptom> childSymptoms;
    
	public Symptom() {
		super();
	}

	public Symptom(Long id, String name, List<Symptom> parentSymptoms, List<Symptom> childSymptoms) {
		super();
		this.id = id;
		this.name = name;
		this.parentSymptoms = parentSymptoms;
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

	public List<Symptom> getParentSymptoms() {
		return parentSymptoms;
	}

	public void setParentSymptoms(List<Symptom> parentSymptoms) {
		this.parentSymptoms = parentSymptoms;
	}

	public List<Symptom> getChildSymptoms() {
		return childSymptoms;
	}

	public void setChildSymptoms(List<Symptom> childSymptoms) {
		this.childSymptoms = childSymptoms;
	}

	@Override
	public String toString() {
		return "Symptom [id=" + id + ", name=" + name + ", parentSymptoms=" + parentSymptoms + ", childSymptoms="
				+ childSymptoms + "]";
	}

}
