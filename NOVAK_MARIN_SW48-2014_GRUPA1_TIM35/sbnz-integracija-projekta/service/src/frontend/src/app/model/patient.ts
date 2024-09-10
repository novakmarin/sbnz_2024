import { Diagnosis } from './diagnosis';
import { Therapy } from './therapy';
import { Symptom } from './symptom';

export class Patient {
  /* id: number;
  healthCardId: string;
  firstName: string;
  lastName: string;
  dob: Date;

  previousDiagnosis: Diagnosis[];
  previousTherapies: Therapy[];
  currentDiagnosis: Diagnosis[];
  currentTherapies: Therapy[];
  previousSymptoms: Symptom[];
  currentSymptoms: Symptom[]; */

  constructor(
    public id?: number,
    public healthCardId?: string,
    public firstName?: string,
    public lastName?: string,
    public dob?: Date,
    public previousDiagnosis?: Diagnosis[],
    public previousTherapies?: Therapy[],
    public currentDiagnosis?: Diagnosis[],
    public currentTherapies?: Therapy[],
    public previousSymptoms?: Symptom[],
    public currentSymptoms?: Symptom[],
    public diagnosis?: Symptom[]
  ) {
    /* this.id = id;
    this.healthCardId = healthCardId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dob = dob;
    this.previousDiagnosis = previousDiagnosis;
    this.previousTherapies = previousTherapies;
    this.currentDiagnosis = currentDiagnosis;
    this.currentTherapies = currentTherapies;
    this.previousSymptoms = previousSymptoms;
    this.currentSymptoms = currentSymptoms; */
  }
}
