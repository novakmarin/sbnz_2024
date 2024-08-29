import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { AppointmentComponent } from '../appointment/appointment.component';
import { RouterOutlet } from '@angular/router';
import { Router } from '@angular/router';
import { DataService } from '../../services/data.service';
import { Doctor } from '../../model/doctor';

const routes: Routes = [
  { path: '', component: AppointmentComponent }, // Default route
  { path: 'appointment', component: AppointmentComponent },
  // Add more routes here if needed
];

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [FormsModule, RouterOutlet],
  providers: [ DataService ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent {
  jmbg: string;
  doctorName: string;
  doctorLastName: string;


  constructor(private router: Router, private dataService: DataService){
    this.jmbg = '';
    this.doctorName = localStorage.getItem('docName') || '';
    this.doctorLastName = localStorage.getItem('docLastName') || '';
  }

  onSubmit(): void {
    
  }
}
