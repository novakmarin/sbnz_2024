import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { PatientService } from '../../services/patient.service';
import { Patient } from '../../model/patient';
import { MessageService } from 'primeng/api';
import { MessagesModule } from 'primeng/messages';
import { SymptomService } from '../../services/symptom.service';
import { Symptom } from '../../model/symptom';

@Component({
  selector: 'app-appointment',
  standalone: true,
  imports: [FormsModule, CommonModule, MessagesModule, AutoCompleteModule],
  providers: [PatientService, MessageService, SymptomService],
  templateUrl: './appointment.component.html',
  styleUrl: './appointment.component.css'
})
export class AppointmentComponent {
  jmbg: string;
  patientChoosen: boolean;
  patient: Patient;
  error: string;
  errorMessage: string;
  msgs: any;
  allSymptoms: Symptom[];

  items: string[] = [];
  filteredItems: any[];
  selectedItem: any;

  constructor(
    private patientService: PatientService,
    private symptomService: SymptomService, 
    private messageService: MessageService)
  {
    this.jmbg = '';
    this.patientChoosen = false;
    this.patient = new Patient();
    this.error = '';
    this.errorMessage = '';
    this.allSymptoms = [];
    this.filteredItems = [];

    this.setAutocompleteItems();
  }

  ngOnInit(): void {
    this.fetchAllSymptoms();
  }

  fetchAllSymptoms(): void{
    this.symptomService.getAllSymptoms().subscribe({
      next: (data: Symptom[]) => {
        this.allSymptoms = data;
        console.log('Symptoms fetched successfully');
      },
      error: (err) => {
        console.error('Error fetching symptoms', err);
      },
      complete: () => {
        console.log('Symptom fetch operation completed.');
        this.setAutocompleteItems();
      }
    });
    
  }

  setAutocompleteItems(): void {
    this.allSymptoms.forEach((symptom, index) => {
      console.log(`Symptom ${index + 1}: ${symptom.name}`);
      this.items.push(symptom.name);
    });
  }

  onSubmit(): void {
    this.getPatient(this.jmbg);
  }

  getPatient(healthCardId: string): void {
    this.patientService.getPatientByHealthCardId(healthCardId).subscribe({
      next: (data: Patient) => {
        this.patient = data;
      },
      error: (error) => {
        console.error('Error fetching patient', error);
        this.messageService.add({severity:'error', summary: 'Pacijent sa navedenim identifikatorom ne postoji!', detail: this.error});
        this.msgs.push({severity:'error', summary:'Info Message', detail:'PrimeNG rocks'});
      },
      complete: () => {
        console.log('Patient retrieval complete');
        this.patientChoosen = true;
      }
    });
  }

  filterItems(event: any): void {
    let query = event.query;
    this.filteredItems = this.items.filter(item => item.toLowerCase().includes(query.toLowerCase()));
  }

  handleKeydown(event: KeyboardEvent) {
    if (event.key === 'Tab') {
      event.preventDefault(); // Prevent default tab action
      const downArrowEvent = new KeyboardEvent('keydown', {
        key: 'ArrowDown',
        code: 'ArrowDown',
        keyCode: 40, // KeyCode for down arrow
        bubbles: true
      });
      event.target?.dispatchEvent(downArrowEvent);
    }
}
}
