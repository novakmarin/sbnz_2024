import { Component } from '@angular/core';
import { InputTextModule } from 'primeng/inputtext';
import { Button } from 'primeng/button';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';
import { MessagesModule } from 'primeng/messages';
import { DataService } from '../services/data.service';
import { Doctor } from '../model/doctor';
import { User } from '../model/user';


@Component({
  selector: 'app-log-in',
  standalone: true,
  imports: [CommonModule, FormsModule, InputTextModule, Button, MessagesModule],
  providers: [MessageService, DataService],
  templateUrl: './log-in.component.html',
  styleUrl: './log-in.component.css'
})
export class LogInComponent {

  username: string;
  password: string;
  error: string;
  msgs: any;
  doctor: Doctor;
  newDoctor: Doctor;
  registrationActive: boolean;
  user: User;

  constructor(private authService: AuthService, private router: Router, private messageService: MessageService, private dataService: DataService) {
    this.username = '';
    this.password = '';
    this.error = '';
    this.msgs = [];
    this.doctor = new Doctor();
    this.newDoctor = new Doctor();
    this.registrationActive = false;
    this.user = new User();
  }

  onSubmit(): void {
    this.authService.login(this.username, this.password).subscribe({
      next: (response) => {
        this.doctor = response;
        localStorage.setItem("doctorName", this.doctor.firstName || '');
        localStorage.setItem("doctorLastName", this.doctor.lastName || '');
        console.log('Login successful');
        this.router.navigate(['/home/appointment']);
      },
      error: (err) => {
        console.error('Login failed', err);
        this.messageService.clear();
        this.messageService.add({severity:'error', summary: 'Neispravni kredencijali!', detail: this.error});
        this.msgs.push({severity:'error', summary:'Info Message', detail:'PrimeNG rocks'});
      }
    });
  }

  toggleReg(){
    if(this.registrationActive){
      this.registrationActive = false;
    }else{
      this.registrationActive = true;
    }
  }

  register(): void {
    this.authService.createDoctor(this.newDoctor).subscribe({
      next: (response) => {
        this.doctor = response;
        console.log('Registration successful');
        this.registrationActive = false;
        this.messageService.clear();
        this.msgs = [];
        this.messageService.add({severity:'success', summary: 'Registracija uspješna. Dobrodošli!', detail: this.error});
        this.msgs.push({severity:'success', summary:'Registracija uspješna. Dobrodošli!', detail:'Sada možete da se prijavite.'});
      },
      error: (err) => {
        console.error('Login failed', err);
        this.messageService.clear();
        this.msgs = [];
        this.messageService.add({severity:'error', summary: 'Korisnik sa unesenim korisničkim imenom već postoji.', detail: this.error});
        this.msgs.push({severity:'error', summary:'Greška prilikom registracije', detail:'Korisnik sa unesenim korisničkim imenom već postoji.'});
      }
    });
  }
}
