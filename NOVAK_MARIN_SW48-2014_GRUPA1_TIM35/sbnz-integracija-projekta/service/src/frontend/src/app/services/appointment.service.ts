import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Appointment } from '../model/appointment';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  private apiUrl = 'http://localhost:8080/appointments';

  constructor(private http: HttpClient) { }

  createAppointment(appointment: Appointment): Observable<Appointment> {
    return this.http.post(this.apiUrl, appointment);
  }

  updateRecommendations(appointment: Appointment): Observable<Appointment>{
    const url = `${this.apiUrl}/updateRecommendations/${appointment.id}`;
    return this.http.put<Appointment>(url, appointment);
  }
}
