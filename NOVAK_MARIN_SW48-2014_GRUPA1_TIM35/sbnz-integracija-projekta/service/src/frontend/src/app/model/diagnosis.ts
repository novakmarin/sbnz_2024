import { Patient } from './patient';
import { Symptom } from './symptom';
import { MentalIllness } from './mental-illness';

export class Diagnosis {
  id: number;
  code: string;
  patient: Patient;
  symptoms: Symptom[];
  mentalIllness: MentalIllness;

  constructor(
    id: number,
    code: string,
    patient: Patient,
    symptoms: Symptom[],
    mentalIllness: MentalIllness
  ) {
    this.id = id;
    this.code = code;
    this.patient = patient;
    this.symptoms = symptoms;
    this.mentalIllness = mentalIllness;
  }
}
