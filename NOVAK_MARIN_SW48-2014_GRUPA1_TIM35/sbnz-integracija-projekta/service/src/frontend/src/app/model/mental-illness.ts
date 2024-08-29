import { Symptom } from './symptom';

export class MentalIllness extends Symptom {
  constructor(
    id: number,
    name: string,
    isAMentalIllness: boolean,
    hasSpecialDiagnostics: boolean,
    childSymptoms: Symptom[]
  ) {
    super(id, name, isAMentalIllness, hasSpecialDiagnostics, childSymptoms);
  }
}
