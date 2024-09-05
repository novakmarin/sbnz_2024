import { Component, WritableSignal } from '@angular/core';
import { CalendarModule } from 'primeng/calendar';
import { Patient } from '../../model/patient';
import { PatientService } from '../../services/patient.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MessagesModule } from 'primeng/messages';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-add-patient',
  standalone: true,
  imports: [CalendarModule, FormsModule, CommonModule, MessagesModule],
  providers: [MessageService],
  templateUrl: './add-patient.component.html',
  styleUrls: ['./add-patient.component.css',
    
  ]
})
export class AddPatientComponent {
  patient = new Patient(0,"","","",new Date(), [], [], [], [], [], [], []);
  error: string;
  msgs: any;

  constructor(private patientService: PatientService, private router: Router, private messageService: MessageService) {
    this.error = "";
    this.msgs = [];
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
}



