package com.ftn.sbnz.service.controller;

import com.ftn.sbnz.model.models.Appointment;
import com.ftn.sbnz.service.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

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
	public Appointment createAppointment(@RequestBody Appointment appointment) {
		return appointmentService.saveAppointment(appointment);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id,
			@RequestBody Appointment appointmentDetails) {
		Optional<Appointment> updatedAppointment = Optional
				.ofNullable(appointmentService.updateAppointment(id, appointmentDetails));
		return updatedAppointment.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
		appointmentService.deleteAppointment(id);
		return ResponseEntity.noContent().build();

	}
}
