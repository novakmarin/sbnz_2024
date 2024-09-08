import { Component, WritableSignal } from '@angular/core';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { CheckboxModule } from 'primeng/checkbox';
import { PatientService } from '../../services/patient.service';
import { Patient } from '../../model/patient';
import { MessageService } from 'primeng/api';
import { MessagesModule } from 'primeng/messages';
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



@Component({
  selector: 'app-add-rule',
  standalone: true,
  imports: [MatCheckboxModule, FormsModule, CommonModule, MessagesModule, AutoCompleteModule, ChipsModule, MatChipsModule,
    MatFormFieldModule, MatIconModule, MatAutocompleteModule, InputTextareaModule
  ],
  providers: [PatientService, MessageService, SymptomService, AppointmentService, NrtmService],
  templateUrl: './add-rule.component.html',
  styleUrl: './add-rule.component.css'
})
export class AddRuleComponent {

  items: string[] = [];
  filteredItems: string[];
  selectedItem: string;
  error: string;
  errorMessage: string;
  allSymptoms: Symptom[];
  symptom: Symptom;
  msgs: any;
  nrtm: NewRuleTemplateModel;

  constructor(
    private patientService: PatientService,
    private symptomService: SymptomService, 
    private messageService: MessageService,
    private appointmentService: AppointmentService,
    private nrtmService: NrtmService)
  {

    this.error = '';
    this.errorMessage = '';
    this.allSymptoms = [];
    this.filteredItems = [];
    this.selectedItem = '';
    this.symptom = new Symptom(0, '', '', false, false, true, []);
    this.nrtm = new NewRuleTemplateModel(1000, '', 1);

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
      const symptom = this.allSymptoms.find(symptom => symptom.name.toLowerCase() === this.selectedItem?.toLowerCase());
          if (symptom && !this.symptom.childSymptoms?.some(symptom1 => symptom1.name === symptom.name)) {
              this.symptom.childSymptoms?.push(symptom);
              this.selectedItem = '';
          }
  }

  removeChip(index: number): void {
    this.symptom.childSymptoms?.splice(index, 1);
  }

  onSubmit(): void {
    this.symptom.customSymptom = true;
    this.symptomService.createSymptom(this.symptom).subscribe({
      next: (data: Symptom) => {
        this.symptom = data;
        this.saveNRTM(this.nrtm);
      },
      error: (error) => {
        console.error('Error saving appointment', error);
        this.messageService.clear();
        this.messageService.add({severity:'error', summary: 'Greska!', detail: this.error});
      },
      complete: () => {
        console.log('Symptom saved successfully');
        this.messageService.clear();
        this.messageService.add({severity:'success', summary: 'Nalaz uspješno sačuvan.', detail: this.error});
        //this.msgs.push({severity:'success', summary:'Info Message', detail:'PrimeNG rocks'});
      }
    });
  }

  saveNRTM(nrtm: NewRuleTemplateModel){
    this.nrtm.symptomName = this.symptom.name;
    this.nrtmService.createNrtm(this.nrtm).subscribe({
      next: (data: NewRuleTemplateModel) => {
        this.nrtm = data;
      },
      error: (error) => {
        console.error('Error saving new rule template model', error);
        this.messageService.clear();
        this.messageService.add({severity:'error', summary: 'Greska!', detail: this.error});
      },
      complete: () => {
        console.log('New rule template model saved successfully');
        this.messageService.clear();
        this.messageService.add({severity:'success', summary: 'Nalaz uspješno sačuvan.', detail: this.error});
        //this.msgs.push({severity:'success', summary:'Info Message', detail:'PrimeNG rocks'});
      }
    });
  }
}
