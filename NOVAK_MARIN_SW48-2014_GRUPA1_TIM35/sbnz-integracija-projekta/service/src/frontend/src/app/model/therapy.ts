import { Symptom } from './symptom';

export class Therapy {
  id?: number;
  isMedication?: boolean;
  name?: string;
  therapyFor?: Symptom[];

  constructor(id?: number, isMedication?: boolean, name?: string, therapyFor?: Symptom[]) {
    this.id = id;
    this.isMedication = isMedication;
    this.name = name;
    this.therapyFor = therapyFor;
  }
}