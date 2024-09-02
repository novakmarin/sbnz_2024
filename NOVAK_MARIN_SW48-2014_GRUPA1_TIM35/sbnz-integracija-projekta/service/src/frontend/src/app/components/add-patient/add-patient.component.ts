import { Component } from '@angular/core';
import { CalendarModule } from 'primeng/calendar';
import { Patient } from '../../model/patient';
import { PatientService } from '../../services/patient.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-patient',
  standalone: true,
  imports: [CalendarModule, FormsModule, CommonModule],
  templateUrl: './add-patient.component.html',
  styleUrls: ['./add-patient.component.css',
    
  ]
})
export class AddPatientComponent {
  patient = new Patient(0,"","","",new Date(), [], [], [], [], [], []);
  
  constructor(private patientService: PatientService, private router: Router) {}

  onSubmit() {
    this.patientService.createPatient(this.patient).subscribe({
      next: (response) => {
        console.log('Patient created successfully', response);
        this.router.navigate(['/home/appointment']);
        // Handle success (e.g., show a success message, navigate to another page)
      },
      error: (error) => {
        console.error('Error creating patient', error);
        // Handle error (e.g., show an error message)
      }
    });
  }
}



