import { Component, WritableSignal } from '@angular/core';
import { CalendarModule } from 'primeng/calendar';
import { Patient } from '../../model/patient';
import { PatientService } from '../../services/patient.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MessagesModule } from 'primeng/messages';
import { MessageService } from 'primeng/api';

import { AutoCompleteModule } from 'primeng/autocomplete';
import { CheckboxModule } from 'primeng/checkbox';
import { SymptomService } from '../../services/symptom.service';
import { Symptom } from '../../model/symptom';
import { ChipsModule } from 'primeng/chips';
import { MatChipsModule } from '@angular/material/chips';
import { MatFormField, MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { AppointmentService } from '../../services/appointment.service';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { NewRuleTemplateModel } from '../../model/newRuleTemplateModel';
import { NrtmService } from '../../services/nrtm.service';
import { Therapy } from '../../model/therapy';
import { TherapyService } from '../../services/therapy.service';


@Component({
  selector: 'app-add-patient',
  standalone: true,
  imports: [CalendarModule, FormsModule, CommonModule, MessagesModule,MatCheckboxModule, AutoCompleteModule, ChipsModule, MatChipsModule,
    MatFormFieldModule, MatIconModule, MatAutocompleteModule, InputTextareaModule],
  providers: [MessageService, PatientService, SymptomService, AppointmentService, NrtmService, TherapyService],
  templateUrl: './add-patient.component.html',
  styleUrls: ['./add-patient.component.css',
    
  ]
})
export class AddPatientComponent {
  patient = new Patient(0,"","","",new Date(), [], [], [], [], [], [], []);
  error: string;
  msgs: any;
  items: string[] = [];
  filteredItems: string[];
  selectedItem: string;

  allTherapies: Therapy[];

  constructor(private patientService: PatientService, private router: Router, private messageService: MessageService, private therapyService: TherapyService) {
    this.error = "";
    this.msgs = [];
    this.filteredItems = [];
    this.selectedItem = '';
    this.allTherapies = [];

    this.setAutocompleteItems();
  }

  ngOnInit(): void {
    this.fetchAllTherapies();
  }

  fetchAllTherapies(): void{
    this.therapyService.getAllTherapies().subscribe({
      next: (data: Therapy[]) => {
        this.allTherapies = data;
        console.log('Therapies fetched successfully');
      },
      error: (err) => {
        console.error('Error fetching therapies', err);
      },
      complete: () => {
        console.log('Therapy fetch operation completed.');
        this.setAutocompleteItems();
      }
    });
    
  }

  setAutocompleteItems(): void {
    this.allTherapies.forEach((therapy, index) => {
      console.log(`Therapy ${index + 1}: ${therapy.name}`);
      this.items.push(therapy.name || '');
    });
  }

  onSubmit() {
    this.patientService.createPatient(this.patient).subscribe({
      next: (response) => {
        console.log('Patient created successfully', response);
        this.router.navigate(['/home/appointment']);
        // Handle success (e.g., show a success message, navigate to another page)
      },
      error: (error) => {
        console.error('Error creating patient', error);
        this.messageService.clear();
        this.messageService.add({severity:'error', summary: 'Pacijent sa navedenim brojem zdravstvene knjižice već postoji.', detail: this.error});
        this.msgs.push({severity:'error', summary:'Info Message', detail:'PrimeNG rocks'});
        // Handle error (e.g., show an error message)
      }
    });
  }

  filterItems(event: any): void {
    let query = event.query;
    this.filteredItems = this.items.filter(item => item.toLowerCase().includes(query.toLowerCase()));
  }

  onKeyDown(event: KeyboardEvent) {
    if (event.key === 'Enter') {
        this.onSelect();
    }
  }
  
  onSelect(){
      const therapy = this.allTherapies.find(therapy => therapy.name?.toLowerCase() === this.selectedItem?.toLowerCase());
          if (therapy && !this.patient.previousTherapies?.some(therapy1 => therapy1.name === therapy?.name)) {
              this.patient?.previousTherapies?.push(therapy);
              this.selectedItem = '';
          }
  }

  removeChip(index: number): void {
    this.patient.previousTherapies?.splice(index, 1);
  }
}



