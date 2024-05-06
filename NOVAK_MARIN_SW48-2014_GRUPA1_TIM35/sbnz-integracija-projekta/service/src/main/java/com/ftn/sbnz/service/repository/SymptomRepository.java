package com.ftn.sbnz.service.repository;

import com.ftn.sbnz.model.models.Symptom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SymptomRepository extends JpaRepository<Symptom, Long> {

    Symptom findByName(String name);

//    // Fetch all symptoms that are parents of a given symptom ID
//    @Query("SELECT s.parentSymptoms FROM Symptom s WHERE s.id = :symptomId")
//    List<Symptom> findAllParentSymptomsBySymptomId(Long symptomId);

    // Fetch all symptoms that are children of a given symptom ID
    @Query("SELECT s.childSymptoms FROM Symptom s WHERE s.id = :symptomId")
    List<Symptom> findAllChildSymptomsBySymptomId(Long symptomId);
}

