package com.ftn.sbnz.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ftn.sbnz.model.models.Patient;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByHealthCardId(String healthCardId);

    List<Patient> findByPreviousDiagnosisId(Long diagnosisId);

    List<Patient> findByCurrentDiagnosisId(Long diagnosisId);

    List<Patient> findByPreviousTherapiesId(Long therapyId);

    List<Patient> findByCurrentTherapiesId(Long therapyId);

    // Find all patients who had a specific symptom previously
    List<Patient> findByPreviousSymptomsId(Long symptomId);

    // Find all patients who currently have a specific symptom
    List<Patient> findByCurrentSymptomsId(Long symptomId);
    
    // Custom query to find patients with specific previous symptoms
    @Query("SELECT DISTINCT p FROM Patient p JOIN p.previousSymptoms ps WHERE ps.id IN :symptomIds")
    List<Patient> findPatientsByPreviousSymptoms(@Param("symptomIds") Set<Long> symptomIds);

    // Custom query to find patients with specific current symptoms
    @Query("SELECT DISTINCT p FROM Patient p JOIN p.currentSymptoms cs WHERE cs.id IN :symptomIds")
    List<Patient> findPatientsByCurrentSymptoms(@Param("symptomIds") Set<Long> symptomIds);

    //Ovako pristupati
    //Set<Long> symptomIds = new HashSet<>(Arrays.asList(1L, 2L, 3L));
    //List<Patient> patientsWithCurrentSymptoms = patientRepository.findPatientsByCurrentSymptoms(symptomIds);
    //List<Patient> patientsWithPreviousSymptoms = patientRepository.findPatientsByPreviousSymptoms(symptomIds);

}
