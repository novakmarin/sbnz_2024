package com.ftn.sbnz.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.sbnz.model.models.Doctor;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{
	Doctor findByUsername(String username);
}
