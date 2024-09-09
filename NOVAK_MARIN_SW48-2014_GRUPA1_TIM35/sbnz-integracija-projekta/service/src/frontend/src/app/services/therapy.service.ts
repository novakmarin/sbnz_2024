import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Therapy } from '../model/therapy';
import { Appointment } from '../model/appointment';

@Injectable({
  providedIn: 'root'
})
export class TherapyService {

  private apiUrl = 'http://localhost:8080/therapies';

  constructor(private http: HttpClient) { }

  getAllTherapies(): Observable<Therapy[]> {
    return this.http.get<Therapy[]>(this.apiUrl);
  }

  getTherapyRecommendations(appointment: Appointment): Observable<Therapy[]>{
    const url = `${this.apiUrl}/suggestTherapies/${appointment.id}`;
    return this.http.put<Therapy[]>(url, appointment);
  }
}
