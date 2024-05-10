package com.ftn.sbnz.service.services;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.sbnz.model.models.Patient;
import com.ftn.sbnz.model.models.Symptom;
import com.ftn.sbnz.service.SampleAppService;
import com.ftn.sbnz.service.repository.PatientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class PatientService {
	
	private static Logger log = LoggerFactory.getLogger(SampleAppService.class);


    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    SymptomService symptomService;
    
    @Autowired
    KieContainer kieContainer;
    
    public Patient addComplexSymptoms(Patient patient) {
    	KieSession kieSession = kieContainer.newKieSession("simpleKsession");
    	ArrayList<Symptom> allSymptoms = (ArrayList<Symptom>) symptomService.findAllSymptoms();
    	for(Symptom s: allSymptoms) {
    		kieSession.insert(s);
    	}
    	kieSession.insert(patient);
    	kieSession.fireAllRules();
    	kieSession.dispose();
    	return patient;
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    // Get a patient by health card ID
    public Patient getPatientByHealthCardId(String healthCardId) {
        return patientRepository.findByHealthCardId(healthCardId);
    }

    // Update a patient
    @Transactional
    public Patient updatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Transactional
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    // Find patients by previous diagnosis ID
    public List<Patient> findPatientsByPreviousDiagnosisId(Long diagnosisId) {
        return patientRepository.findByPreviousDiagnosisId(diagnosisId);
    }

    // Find patients by current diagnosis ID
    public List<Patient> findPatientsByCurrentDiagnosisId(Long diagnosisId) {
        return patientRepository.findByCurrentDiagnosisId(diagnosisId);
    }

    // Find patients by previous therapy ID
    public List<Patient> findPatientsByPreviousTherapiesId(Long therapyId) {
        return patientRepository.findByPreviousTherapiesId(therapyId);
    }

    // Find patients by current therapy ID
    public List<Patient> findPatientsByCurrentTherapiesId(Long therapyId) {
        return patientRepository.findByCurrentTherapiesId(therapyId);
    }

    // Find patients by previous symptom ID
    public List<Patient> findPatientsByPreviousSymptomsId(Long symptomId) {
        return patientRepository.findByPreviousSymptomsId(symptomId);
    }

    // Find patients by current symptom ID
    public List<Patient> findPatientsByCurrentSymptomsId(Long symptomId) {
        return patientRepository.findByCurrentSymptomsId(symptomId);
    }

    // Find patients by a set of previous symptoms
    public List<Patient> findPatientsByPreviousSymptoms(Set<Long> symptomIds) {
        return patientRepository.findPatientsByPreviousSymptoms(symptomIds);
    }

    // Find patients by a set of current symptoms
    public List<Patient> findPatientsByCurrentSymptoms(Set<Long> symptomIds) {
        return patientRepository.findPatientsByCurrentSymptoms(symptomIds);
    }
    
    private void releaseObjectsFromSession(KieSession kieSession){
        kieSession.getObjects();

        for( Object object: kieSession.getObjects() ){
            kieSession.delete( kieSession.getFactHandle( object ) );
        }
    }
}

