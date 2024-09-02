import { Component } from '@angular/core';
import { Patient } from '../../model/patient';
import { PatientService } from '../../services/patient.service';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { TableModule } from 'primeng/table';
import { CommonModule } from '@angular/common';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { FormsModule } from '@angular/forms';
import { Symptom } from '../../model/symptom';
import { SymptomService } from '../../services/symptom.service';

@Component({
  selector: 'app-reports',
  standalone: true,
  imports: 
  [
    MatTableModule,
    MatCardModule,
    TableModule,
    CommonModule,
    AutoCompleteModule,
    FormsModule
  ],
  providers:[PatientService, SymptomService],
  templateUrl: './reports.component.html',
  styleUrl: './reports.component.css'
})
export class ReportsComponent {

  patients: Patient[];
  displayedColumns: string[] = ['healthCardId', 'firstName', 'lastName', 'dob', 'currentSymptoms'];
  symptomName: string;
  symptomSelected: boolean;
  items: string[] = [];
  filteredItems: string[];
  selectedItem: string;
  allSymptoms: Symptom[];

  constructor(private patientService: PatientService, private symptomService: SymptomService){
    this.filteredItems = [];
    this.selectedItem = '';
    this.patients = [];
    this.allSymptoms = [];
    this.symptomSelected = false;
    this.symptomName = "";
    this.fetchAllSymptoms();
  }

  fetchPatients(symptom: Symptom){
    this.patientService.getPatientsWithSymptom(symptom.name).subscribe({
      next: (data: Patient[]) => {
        this.symptomName = symptom.name;
        this.patients = data;
        console.log('Patients fetched successfully');
      },
      error: (err) => {
        console.error('Error fetching patients', err);
      },
      complete: () => {
        console.log('Patient fetch operation completed.');
      }
    });
  }

  filterItems(event: any): void {
    let query = event.query;
    this.filteredItems = this.items.filter(item => item.toLowerCase().includes(query.toLowerCase()));
  }
  onKeyDown(event: KeyboardEvent) {
    if (event.key === 'Enter') {
        const symptom = this.allSymptoms.find(symptom => symptom.name.toLowerCase() === this.selectedItem?.toLowerCase());
        if (symptom) {
            console.log(symptom);
            // Optionally clear the selected item after adding
            this.selectedItem = '';
        }
    }
  }

  onSelect(){
    const symptom = this.allSymptoms.find(symptom => symptom.name.toLowerCase() === this.selectedItem?.toLowerCase());
        if (symptom) {
            console.log(symptom);
            console.log(symptom.amentalIllness);
            this.fetchPatients(symptom);
            // Optionally clear the selected item after adding
            this.selectedItem = '';
        }
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
}
