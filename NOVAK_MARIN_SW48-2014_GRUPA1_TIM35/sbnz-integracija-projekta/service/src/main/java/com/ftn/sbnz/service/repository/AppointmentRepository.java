package com.ftn.sbnz.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.sbnz.model.models.Appointment;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	Optional<Appointment> findById(Long appointmentId);
	
    List<Appointment> findByDoctorId(Long doctorId);

    List<Appointment> findByPatientId(Long patientId);

    List<Appointment> findByDate(Date date);

    // Find appointments between two dates
    List<Appointment> findByDateBetween(Date startDate, Date endDate);

    // Fetch all appointments including symptoms for a specific patient
    @Query("SELECT a FROM Appointment a JOIN FETCH a.currentSymptoms WHERE a.patient.id = :patientId")
    List<Appointment> findAllWithSymptomsByPatientId(Long patientId);
}

