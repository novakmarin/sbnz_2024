import { Symptom } from './symptom';

export class MentalIllness extends Symptom {
  constructor(
    id: number,
    code: string,
    name: string,
    isAMentalIllness: boolean,
    hasSpecialDiagnostics: boolean,
    isCustomSymptom: boolean,
    childSymptoms: Symptom[]
  ) {
    super(id, code, name, isAMentalIllness, hasSpecialDiagnostics, isCustomSymptom, childSymptoms);
  }
}
