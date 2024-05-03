package com.ftn.sbnz.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.sbnz.model.models.Symptom;
import com.ftn.sbnz.service.repository.SymptomRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SymptomService {

    private final SymptomRepository symptomRepository;

    @Autowired
    public SymptomService(SymptomRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
    }

    public Symptom saveSymptom(Symptom symptom) {
        return symptomRepository.save(symptom);
    }

    public Optional<Symptom> findSymptomById(Long id) {
        return symptomRepository.findById(id);
    }

    public Optional<Symptom> findSymptomByName(String name) {
        return symptomRepository.findByName(name);
    }

    public void deleteSymptom(Long id) {
        symptomRepository.deleteById(id);
    }

    // Retrieve all parent symptoms of a given symptom ID
    public List<Symptom> findAllParentSymptomsBySymptomId(Long symptomId) {
        return symptomRepository.findAllParentSymptomsBySymptomId(symptomId);
    }

    // Retrieve all child symptoms of a given symptom ID
    public List<Symptom> findAllChildSymptomsBySymptomId(Long symptomId) {
        return symptomRepository.findAllChildSymptomsBySymptomId(symptomId);
    }

    public List<Symptom> findAllSymptoms() {
        return symptomRepository.findAll();
    }
}

