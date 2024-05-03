package com.ftn.sbnz.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.sbnz.model.models.Diagnosis;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

	Optional<Diagnosis> findById(Long id);
	
    Optional<Diagnosis> findByCode(String code);

    List<Diagnosis> findByPatientId(Long patientId);

    // Find all diagnoses related to a specific mental illness
    List<Diagnosis> findByMentalIllnessId(Long mentalIllnessId);

    // Fetch all diagnoses that include a specific symptom
    @Query("SELECT d FROM Diagnosis d JOIN d.symptoms s WHERE s.id = :symptomId")
    List<Diagnosis> findAllBySymptomId(Long symptomId);
}

