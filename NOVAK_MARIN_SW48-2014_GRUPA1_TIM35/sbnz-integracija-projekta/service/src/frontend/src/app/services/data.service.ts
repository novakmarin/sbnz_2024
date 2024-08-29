import { Injectable } from '@angular/core';
import { Doctor } from '../model/doctor';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  activeDoctor: Doctor;


  constructor() { 
    this.activeDoctor = new Doctor();
    console.log("DATA SERVICE");
  }
}
