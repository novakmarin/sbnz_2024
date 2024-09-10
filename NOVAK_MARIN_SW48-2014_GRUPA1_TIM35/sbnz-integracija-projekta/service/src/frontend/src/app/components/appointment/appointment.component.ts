import { Component, WritableSignal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { PatientService } from '../../services/patient.service';
import { Patient } from '../../model/patient';
import { MessageService } from 'primeng/api';
import { MessagesModule } from 'primeng/messages';
import { SymptomService } from '../../services/symptom.service';
import { Symptom } from '../../model/symptom';
import { ChipsModule } from 'primeng/chips';
import { MatChipsModule } from '@angular/material/chips';

//For chips
import {LiveAnnouncer} from '@angular/cdk/a11y';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {ChangeDetectionStrategy, computed, inject, model, signal} from '@angular/core';
import {MatAutocompleteModule, MatAutocompleteSelectedEvent} from '@angular/material/autocomplete';
import {MatChipInputEvent} from '@angular/material/chips';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';
import { Appointment } from '../../model/appointment';
import { CalendarModule } from 'primeng/calendar';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { AppointmentService } from '../../services/appointment.service';
import { Therapy } from '../../model/therapy';
import { TherapyService } from '../../services/therapy.service';


@Component({
  selector: 'app-appointment',
  standalone: true,
  imports: [FormsModule, CommonModule, MessagesModule, AutoCompleteModule, ChipsModule, MatChipsModule
    ,MatFormFieldModule, MatIconModule, MatAutocompleteModule, CalendarModule, InputTextareaModule
  ],
  providers: [PatientService, MessageService, SymptomService, AppointmentService, TherapyService],
  templateUrl: './appointment.component.html',
  styleUrl: './appointment.component.css'
})
export class AppointmentComponent {
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];
  readonly currentFruit = model('');
  readonly fruits: WritableSignal<string[]>;
  readonly allFruits: string[] = ['Apple', 'Lemon', 'Lime', 'Orange', 'Strawberry'];
  readonly filteredFruits = computed(() => {
    const currentFruit = this.currentFruit().toLowerCase();
    return currentFruit
      ? this.patientsSymptomsNames.filter(fruit => fruit.toLowerCase().includes(currentFruit))
      : this.patientsSymptomsNames.slice();
  });

  readonly announcer = inject(LiveAnnouncer);

  appointment: Appointment;
  jmbg: string;
  patientChoosen: boolean;
  patient: Patient;
  error: string;
  errorMessage: string;
  msgs: any;
  allSymptoms: Symptom[];
  patientsSymptoms: Symptom[];
  patientsSymptomsNames: string[] = [];
  suggestedTherapies: Therapy[];

  items: string[] = [];
  filteredItems: string[];
  selectedItem: string;

  constructor(
    private patientService: PatientService,
    private symptomService: SymptomService, 
    private messageService: MessageService,
    private appointmentService: AppointmentService,
    private therapyService: TherapyService)
  {
    this.jmbg = '';
    this.patientChoosen = false;
    this.patient = new Patient();
    this.error = '';
    this.errorMessage = '';
    this.allSymptoms = [];
    this.patientsSymptoms = [];
    this.filteredItems = [];
    this.selectedItem = '';
    this.appointment = new Appointment();
    this.appointment.id = 100;
    this.appointment.date = new Date();
    this.suggestedTherapies = [];

    this.fruits = signal(this.patientsSymptomsNames);

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

  onAppointmentSubmit(): void{
    this.saveAppointment();
  }

  saveAppointment(): void{
    this.appointmentService.createAppointment(this.appointment).subscribe({
      next: (data: Appointment) => {
        this.appointment = data;
      },
      error: (error) => {
        console.error('Error saving appointment', error);
        this.messageService.clear();
        this.messageService.add({severity:'error', summary: 'Greska!', detail: this.error});
      },
      complete: () => {
        console.log('Appointment saved successfully');
        this.patientChoosen = true;
        this.messageService.clear();
        this.messageService.add({severity:'success', summary: 'Nalaz uspješno sačuvan.', detail: this.error});
        //this.msgs.push({severity:'success', summary:'Info Message', detail:'PrimeNG rocks'});
      }
    });
  }

  getTherapyRecommendations(): void{
    this.therapyService.getTherapyRecommendations(this.appointment).subscribe({
      next: (data: Therapy[]) => {
        this.suggestedTherapies = data;
        this.appointment.patient = this.patient;
      },
      error: (error) => {
        console.error('Error getting therapy recommendations', error);
        this.messageService.add({severity:'error', summary: 'Greška!', detail: this.error});
        this.msgs.push({severity:'error', summary:'Info Message', detail:'PrimeNG rocks'});
      },
      complete: () => {
        console.log('Recommendation retrieval complete');
        this.patientChoosen = true;
        this.patient.currentSymptoms?.forEach((symptom, index) =>{
          this.patientsSymptomsNames.push(symptom.name);
        });
      }
    });
  }

  getPatient(healthCardId: string): void {
    this.patientService.getPatientByHealthCardId(healthCardId).subscribe({
      next: (data: Patient) => {
        this.patient = data;
        this.appointment.patient = this.patient;
      },
      error: (error) => {
        console.error('Error fetching patient', error);
        this.messageService.add({severity:'error', summary: 'Pacijent sa navedenim identifikatorom ne postoji!', detail: this.error});
        this.msgs.push({severity:'error', summary:'Info Message', detail:'PrimeNG rocks'});
      },
      complete: () => {
        console.log('Patient retrieval complete');
        this.patientChoosen = true;
        this.patient.currentSymptoms?.forEach((symptom, index) =>{
          this.patientsSymptomsNames.push(symptom.name);
        });
      }
    });
  }

  filterItems(event: any): void {
    let query = event.query;
    this.filteredItems = this.items.filter(item => item.toLowerCase().includes(query.toLowerCase()));
  }


addChip(event: any) {
  
}

onKeyDown(event: KeyboardEvent) {
  if (event.key === 'Enter') {
      this.onSelect();
  }
}

/* onSelect(){
  const symptom = this.allSymptoms.find(symptom => symptom.name.toLowerCase() === this.selectedItem?.toLowerCase());
      if (symptom && !this.patient.currentSymptoms?.includes(symptom)) {
          console.log(symptom);
          console.log(symptom.amentalIllness);
          this.patient.currentSymptoms?.push(symptom);
          this.appointment.currentSymptoms = this.patient.currentSymptoms;
          this.patientService.updateRecommendations(this.patient).subscribe({
            next: (data: Patient) => {
              this.patient = data;
              this.appointment.patient = this.patient;
            },
            error: (error) => {
              console.error('Error fetching patient', error);
              this.messageService.add({severity:'error', summary: 'Greska!', detail: this.error});
            },
            complete: () => {
              console.log('Patient retrieval complete');
              this.patientChoosen = true;
            }
          });
          // Optionally clear the selected item after adding
          this.selectedItem = '';
      }
} */

  onSelect(){
    const symptom = this.allSymptoms.find(symptom => symptom.name.toLowerCase() === this.selectedItem?.toLowerCase());
        if (symptom && !this.patient.currentSymptoms?.some(symptom1 => symptom1.name === symptom.name)) {
            console.log(symptom);
            console.log(symptom.amentalIllness);
            this.patient.currentSymptoms?.push(symptom);
            this.appointment.currentSymptoms = this.patient.currentSymptoms;
            this.appointmentService.updateRecommendations(this.appointment).subscribe({
              next: (data: Appointment) => {
                this.appointment = data;
                this.patient = this.appointment.patient as Patient;
              },
              error: (error) => {
                console.error('Error updating appointment', error);
                this.messageService.add({severity:'error', summary: 'Greska!', detail: this.error});
              },
              complete: () => {
                console.log('Appointment update complete');
                this.patientChoosen = true;
              }
            });
            // Optionally clear the selected item after adding
            this.selectedItem = '';
        }
  }

  refresh(){
    this.appointment.currentSymptoms = this.patient.currentSymptoms;
            this.appointmentService.updateRecommendations(this.appointment).subscribe({
              next: (data: Appointment) => {
                this.appointment = data;
                this.patient = this.appointment.patient as Patient;
              },
              error: (error) => {
                console.error('Error updating appointment', error);
                this.messageService.add({severity:'error', summary: 'Greska!', detail: this.error});
              },
              complete: () => {
                console.log('Appointment update complete');
                this.patientChoosen = true;
              }
            });
            // Optionally clear the selected item after adding
            this.selectedItem = '';
  }

  onChipClick(symptom: Symptom){
    this.appointment?.patient?.diagnosis?.push(symptom);
    this.getTherapyRecommendations();
  }

  onTherapyChipClick(therapy: Therapy){
    this.appointment?.patient?.currentTherapies?.push(therapy);
  }

add(event: MatChipInputEvent): void {
  const value = (event.value || '').trim();

  // Add our fruit
  if (value) {
    this.fruits.update(fruits => [...fruits, value]);
  }

  // Clear the input value
  this.currentFruit.set('');
}

remove(fruit: string): void {
  this.fruits.update(fruits => {
    const index = fruits.indexOf(fruit);
    if (index < 0) {
      return fruits;
    }

    fruits.splice(index, 1);
    this.announcer.announce(`Removed ${fruit}`);
    return [...fruits];
  });
}

selected(event: MatAutocompleteSelectedEvent): void {
  this.fruits.update(fruits => [...fruits, event.option.viewValue]);
  this.currentFruit.set('');
  event.option.deselect();
}

removeChip(index: number): void {
  this.patient.currentSymptoms?.splice(index, 1);
}

removeDiagnosis(index: number): void {
  this.patient.diagnosis?.splice(index, 1);
}

removeTherapy(index: number): void {
  this.patient.currentTherapies?.splice(index, 1);
}






}
