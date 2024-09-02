package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.models.Appointment;
import com.ftn.sbnz.model.models.Patient;
import com.ftn.sbnz.service.services.AppointmentService;
import com.ftn.sbnz.service.services.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private PatientService patientService;

	@GetMapping
	public List<Appointment> getAllAppointments() {
		return appointmentService.findAllAppointments();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
		Optional<Appointment> appointment = appointmentService.findAppointmentById(id);
		return appointment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PostMapping
	public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
		System.out.println("PRIJE");
		patientService.updatePatient(appointment.getPatient().getId(), appointment.getPatient());
		System.out.println("POSLIJE");
		Appointment savedAppointment = appointmentService.saveAppointment(appointment);
		return ResponseEntity.ok(savedAppointment);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id,
			@RequestBody Appointment appointmentDetails) {
		Optional<Appointment> updatedAppointment = Optional
				.ofNullable(appointmentService.updateAppointment(id, appointmentDetails));
		return updatedAppointment.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
    @PutMapping("/updateRecommendations/{id}")
    public ResponseEntity<Appointment> updateRecommendations(@PathVariable Long id, @RequestBody Appointment appointment) {
//    	Patient updatedPatient = patient;
//        updatedPatient.setCurrentSymptoms(new ArrayList<Symptom>());
//        for(Symptom s: patient.getCurrentSymptoms()) {
//        	updatedPatient.getCurrentSymptoms().add(s);
//        }
        Appointment updatedAppointment = appointmentService.fireRules(appointment);
        return updatedAppointment != null ? new ResponseEntity<>(updatedAppointment, HttpStatus.OK) 
                                      : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
		appointmentService.deleteAppointment(id);
		return ResponseEntity.noContent().build();

	}
}
