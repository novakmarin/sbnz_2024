import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Patient } from '../model/patient';
import { Observable } from 'rxjs';
import { Symptom } from '../model/symptom';

@Injectable({
  providedIn: 'root'
})
export class SymptomService {

  private apiUrl = 'http://localhost:8080/symptoms';

  constructor(private http: HttpClient) {}

  createSymptom(symptom: Symptom): Observable<any>{
    return this.http.post(this.apiUrl, symptom);
  }

  getAllSymptoms(): Observable<Symptom[]> {
    return this.http.get<Symptom[]>(this.apiUrl);
  }

  
  
}
