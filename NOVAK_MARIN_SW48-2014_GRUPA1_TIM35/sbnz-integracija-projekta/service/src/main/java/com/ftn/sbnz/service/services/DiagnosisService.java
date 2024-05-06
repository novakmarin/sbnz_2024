package com.ftn.sbnz.service.services;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.sbnz.model.models.Diagnosis;
import com.ftn.sbnz.service.repository.DiagnosisRepository;
import com.ftn.sbnz.service.repository.SymptomRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    
    @Autowired
    SymptomService symptomService;
    
    @Autowired
    PatientService patientService;

    @Autowired
    public DiagnosisService(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    public Diagnosis saveDiagnosis(Diagnosis diagnosis) {
        return diagnosisRepository.save(diagnosis);
    }

    public Optional<Diagnosis> findDiagnosisById(Long id) {
        return diagnosisRepository.findById(id);
    }

    public Optional<Diagnosis> findDiagnosisByCode(String code) {
        return diagnosisRepository.findByCode(code);
    }

    public void deleteDiagnosis(Long id) {
        diagnosisRepository.deleteById(id);
    }

    // List all diagnoses for a specific patient
    public List<Diagnosis> findAllDiagnosesByPatientId(Long patientId) {
        return diagnosisRepository.findByPatientId(patientId);
    }

    // List all diagnoses related to a specific mental illness
    public List<Diagnosis> findAllDiagnosesByMentalIllnessId(Long mentalIllnessId) {
        return diagnosisRepository.findByMentalIllnessId(mentalIllnessId);
    }

    // Fetch all diagnoses that include a specific symptom
    public List<Diagnosis> findAllDiagnosesBySymptomId(Long symptomId) {
        return diagnosisRepository.findAllBySymptomId(symptomId);
    }
}

