import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Patient } from '../model/patient';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private apiUrl = 'http://localhost:8080/patients';

  constructor(private http: HttpClient) { }

  createPatient(patient: Patient): Observable<any> {
    return this.http.post(this.apiUrl, patient, { observe: 'response' });
  }

  getPatientByHealthCardId(healthCardId: string): Observable<Patient> {
    const url = `${this.apiUrl}/getPatientByHcidOrJmbg/${healthCardId}`;
    return this.http.get<Patient>(url);
  }

  updateRecommendations(patient: Patient): Observable<Patient>{
    const url = `${this.apiUrl}/updateRecommendations/${patient.id}`;
    return this.http.put<Patient>(url, patient);
  }

  getPatientsWithSymptom(symptomName: string): Observable<Patient[]> {
    const url = `${this.apiUrl}/getPatientsWithSymptom/${symptomName}`;
    return this.http.get<Patient[]>(url);
  }
}
