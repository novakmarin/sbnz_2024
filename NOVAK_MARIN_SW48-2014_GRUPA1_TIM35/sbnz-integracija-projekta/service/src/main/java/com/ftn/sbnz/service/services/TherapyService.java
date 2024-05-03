package com.ftn.sbnz.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.sbnz.model.models.Therapy;
import com.ftn.sbnz.service.repository.TherapyRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TherapyService {

    private final TherapyRepository therapyRepository;

    @Autowired
    public TherapyService(TherapyRepository therapyRepository) {
        this.therapyRepository = therapyRepository;
    }

    public Therapy saveTherapy(Therapy therapy) {
        return therapyRepository.save(therapy);
    }

    public Optional<Therapy> getTherapyById(Long id) {
        return therapyRepository.findById(id);
    }

    public Optional<Therapy> getTherapyByName(String name) {
        return therapyRepository.findByName(name);
    }

    // Retrieve all therapies that are medications
    public List<Therapy> getTherapiesByIsMedication(boolean isMedication) {
        return therapyRepository.findByIsMedication(isMedication);
    }

    public List<Therapy> getAllTherapies() {
        return therapyRepository.findAll();
    }

    public void deleteTherapy(Long id) {
        therapyRepository.deleteById(id);
    }
}

