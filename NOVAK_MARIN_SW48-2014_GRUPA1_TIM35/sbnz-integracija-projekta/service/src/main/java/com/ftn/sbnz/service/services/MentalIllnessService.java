package com.ftn.sbnz.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.sbnz.model.models.MentalIllness;
import com.ftn.sbnz.model.models.Symptom;
import com.ftn.sbnz.service.repository.MentalIllnessRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MentalIllnessService {

    private final MentalIllnessRepository mentalIllnessRepository;

    @Autowired
    public MentalIllnessService(MentalIllnessRepository mentalIllnessRepository) {
        this.mentalIllnessRepository = mentalIllnessRepository;
    }

    public MentalIllness saveMentalIllness(MentalIllness mentalIllness) {
        return mentalIllnessRepository.save(mentalIllness);
    }

    public Optional<MentalIllness> findMentalIllnessById(Long id) {
        return mentalIllnessRepository.findById(id);
    }

    public Optional<MentalIllness> findMentalIllnessByName(String name) {
        return mentalIllnessRepository.findByName(name);
    }

    public List<MentalIllness> findAllMentalIllnesses() {
        return mentalIllnessRepository.findAll();
    }

    public void deleteMentalIllnessById(Long id) {
        mentalIllnessRepository.deleteById(id);
    }

    // Retrieve all parent symptoms of a given MentalIllness ID
    public List<Symptom> findAllParentSymptomsByMentalIllnessId(Long mentalIllnessId) {
        return mentalIllnessRepository.findAllParentSymptomsBySymptomId(mentalIllnessId);
    }

    // Retrieve all child symptoms of a given MentalIllness ID
    public List<Symptom> findAllChildSymptomsByMentalIllnessId(Long mentalIllnessId) {
        return mentalIllnessRepository.findAllChildSymptomsBySymptomId(mentalIllnessId);
    }
}

