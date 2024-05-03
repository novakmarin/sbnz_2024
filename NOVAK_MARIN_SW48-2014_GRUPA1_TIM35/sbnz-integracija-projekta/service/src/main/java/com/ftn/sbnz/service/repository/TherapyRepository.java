package com.ftn.sbnz.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.sbnz.model.models.Therapy;

import java.util.List;
import java.util.Optional;

@Repository
public interface TherapyRepository extends JpaRepository<Therapy, Long> {

    // Find a therapy by its name
    Optional<Therapy> findByName(String name);

    // Find therapies based on whether they are medication or not
    List<Therapy> findByIsMedication(boolean isMedication);
}

