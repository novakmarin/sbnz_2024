package com.ftn.sbnz.service.services;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.sbnz.model.models.Appointment;
import com.ftn.sbnz.model.models.Symptom;
import com.ftn.sbnz.service.repository.AppointmentRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    
    @Autowired
    PatientService patientService;
    
    @Autowired
    SymptomService symptomService;
    
    @Autowired
    KieContainer kieContainer;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
    

    public Appointment saveAppointment(Appointment appointment) {
    
        return appointmentRepository.save(appointment);
    }
    
    public Appointment fireRules(Appointment appointment) {
    	KieSession kieSession = kieContainer.newKieSession("simpleKsession");
    	ArrayList<Symptom> allSymptoms = (ArrayList<Symptom>) symptomService.findAllSymptoms();
    	ArrayList<Appointment> allAppointments = (ArrayList<Appointment>) appointmentRepository.findAll();
    	for(Symptom s: allSymptoms) {
    		kieSession.insert(s);
    	}
    	for(Appointment a: allAppointments) {
    		kieSession.insert(a);
    	}
    	kieSession.insert(appointment);
    	kieSession.insert(appointment.getPatient());
    	kieSession.fireAllRules();
    	kieSession.dispose();
    	return appointment;
    }

    public Optional<Appointment> findAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    public List<Appointment> findAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> findAppointmentsByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    public List<Appointment> findAppointmentsByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    // Retrieve appointments by specific date
    public List<Appointment> findAppointmentsByDate(Date date) {
        return appointmentRepository.findByDate(date);
    }

    // Retrieve appointments between two dates
    public List<Appointment> findAppointmentsBetweenDates(Date startDate, Date endDate) {
        return appointmentRepository.findByDateBetween(startDate, endDate);
    }

    // Retrieve all appointments including symptoms for a specific patient
    public List<Appointment> findAllWithSymptomsByPatientId(Long patientId) {
        return appointmentRepository.findAllWithSymptomsByPatientId(patientId);
    }

    public Appointment updateAppointment(Long appointmentId, Appointment updatedAppointment) {
        return appointmentRepository.findById(appointmentId)
            .map(appointment -> {
                appointment.setDate(updatedAppointment.getDate());
                appointment.setDoctor(updatedAppointment.getDoctor());
                appointment.setPatient(updatedAppointment.getPatient());
                appointment.setDiagnosis(updatedAppointment.getDiagnosis());
                appointment.setCurrentSymptoms(updatedAppointment.getCurrentSymptoms());
                appointment.setNote(updatedAppointment.getNote());
                return appointmentRepository.save(appointment);
            })
            .orElseGet(() -> {
                updatedAppointment.setId(appointmentId);
                return appointmentRepository.save(updatedAppointment);
            });
    }

    public void deleteAppointment(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }
}

