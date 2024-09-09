package com.ftn.sbnz.service.services;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.sbnz.model.models.Appointment;
import com.ftn.sbnz.model.models.Symptom;
import com.ftn.sbnz.model.models.Therapy;
import com.ftn.sbnz.service.repository.TherapyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TherapyService {

	@Autowired
    KieContainer kieContainer;
	
	@Autowired
    TherapyRepository therapyRepository;
    
	@Autowired
    SymptomService symptomService;
	
	@Autowired
	AppointmentService appointmentService;
    
	public List<Therapy> suggestTherapies(Appointment appointment) {
    	
		System.out.println("USLO U THERAPY SERVICE");
    	KieSession kieSession = kieContainer.newKieSession("simpleKsession");
    	List<Symptom> allSymptoms = symptomService.findAllSymptoms();
    	List<Appointment> allAppointments = appointmentService.findAllAppointments();
    	List<Therapy> allTherapies = this.getAllTherapies();
    	for(Symptom s: allSymptoms) {
    		kieSession.insert(s);
    	}
    	for(Appointment a: allAppointments) {
    		System.out.println("START");
    		System.out.println(a.getDate().toString());
    		System.out.println("END");
    		kieSession.insert(a);
    	}
    	for(Therapy t: allTherapies) {
    		kieSession.insert(t);
    	}
    	List<Therapy> therapySuggestions = new ArrayList<Therapy>();

    	kieSession.insert(therapySuggestions);
    	kieSession.insert(appointment);
    	kieSession.insert(appointment.getPatient());
    	
    	kieSession.fireAllRules();
    	kieSession.dispose();
    	System.out.println(therapySuggestions);
    	return therapySuggestions;
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
    
    public Therapy updateTherapy(Therapy therapy) {
    	Therapy updatedTherapy = therapyRepository.findById(therapy.getId()).get();
    	for(Symptom s: therapy.getTherapyFor()) {
    		if(!updatedTherapy.getTherapyFor().contains(s)) {
    			updatedTherapy.getTherapyFor().add(symptomService.findSymptomByName(s.getName()));
    		}
    	}
    	return this.saveTherapy(updatedTherapy);
    }

    public void deleteTherapy(Long id) {
        therapyRepository.deleteById(id);
    }
}

