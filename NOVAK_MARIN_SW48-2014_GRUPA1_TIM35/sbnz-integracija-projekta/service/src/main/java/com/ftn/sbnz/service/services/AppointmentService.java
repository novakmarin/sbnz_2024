package com.ftn.sbnz.service.services;

import org.kie.api.runtime.KieContainer;


import java.io.InputStream;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.ObjectDataCompiler;
import org.drools.template.objects.ArrayDataProvider;

import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ftn.sbnz.model.models.Appointment;
import com.ftn.sbnz.model.models.NewRuleTemplateModel;
import com.ftn.sbnz.model.models.Symptom;
import com.ftn.sbnz.model.models.Patient;
import com.ftn.sbnz.service.repository.AppointmentRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppointmentService {

	@Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    PatientService patientService;
    
    @Autowired
    SymptomService symptomService;
    
    @Autowired
    NewRuleTemplateModelService nrtmService;
    
    @Autowired
    KieContainer kieContainer;
    
    public Appointment saveAppointment(Appointment appointment) {
    	
    	List<Symptom> symptoms = new ArrayList<Symptom>();
    	List<Symptom> diagnosis = new ArrayList<Symptom>();
    	for(Symptom s: appointment.getPatient().getCurrentSymptoms()) {
    		symptoms.add(symptomService.findSymptomByName(s.getName()));
    	}
    	for(Symptom s: appointment.getPatient().getDiagnosis()) {
    		diagnosis.add(symptomService.findSymptomByName(s.getName()));
    	}
    	appointment.setCurrentSymptoms(symptoms);
    	Patient patient = patientService.getPatientById(appointment.getPatient().getId()).get();
    	patient.setCurrentSymptoms(symptoms);
    	patient.setDiagnosis(diagnosis);
    	appointment.setPatient(patient);
    	
        return appointmentRepository.save(appointment);
    }
    
    public Appointment fireRules(Appointment appointment) {
    	InputStream template = AppointmentService.class.getResourceAsStream("/rules/templatetable/new-rule.drt");
        List<NewRuleTemplateModel> data = nrtmService.getAll();
        
        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);
        
        System.out.println(drl);
        
        KieSession ksession = createKieSessionFromDRL(drl);
    	
    	KieSession kieSession = kieContainer.newKieSession("simpleKsession");
    	List<Symptom> allSymptoms = symptomService.findAllSymptoms();
    	List<Appointment> allAppointments = this.findAllAppointments();
    	for(Symptom s: allSymptoms) {
    		kieSession.insert(s);
    		ksession.insert(s);
    	}
    	for(Appointment a: allAppointments) {
    		System.out.println("APPOINTMENTS START");
    		System.out.println(a.getDate().toString());
    		System.out.println("APPOINTMENTS END");
    		kieSession.insert(a);
    		ksession.insert(a);
    	}

    	kieSession.insert(appointment);
    	kieSession.insert(appointment.getPatient());
    	
    	ksession.insert(appointment);
    	ksession.insert(appointment.getPatient());
    	kieSession.fireAllRules();
    	kieSession.dispose();
    	ksession.fireAllRules();
    	ksession.dispose();
    	return appointment;
    }
    
    public void testSimpleTemplateWithObjects(){
        
        InputStream template = AppointmentService.class.getResourceAsStream("/rules/templatetable/new-rule.drt");

        
        List<NewRuleTemplateModel> data = new ArrayList<NewRuleTemplateModel>();
        
        data.add(new NewRuleTemplateModel(1L, "OCD", 2));
        
        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);
        
        System.out.println(drl);
        
        KieSession ksession = createKieSessionFromDRL(drl);
        ksession.fireAllRules();
        ksession.dispose();
        
    }

	private KieSession createKieSessionFromDRL(String drl){
	    KieHelper kieHelper = new KieHelper();
	    kieHelper.addContent(drl, ResourceType.DRL);
	    
	    Results results = kieHelper.verify();
	    
	    if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
	        List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
	        for (Message message : messages) {
	            System.out.println("Error: "+message.getText());
	        }
	        
	        throw new IllegalStateException("Compilation errors were found. Check the logs.");
	    }
	    
	    return kieHelper.build().newKieSession();
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

