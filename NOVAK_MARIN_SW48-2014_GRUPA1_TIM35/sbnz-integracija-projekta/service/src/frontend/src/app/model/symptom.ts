export class Symptom {
    id: number;
    name: string;
    isAMentalIllness: boolean;
    hasSpecialDiagnostics: boolean;
    childSymptoms: Symptom[];
  
    constructor(
      id: number,
      name: string,
      isAMentalIllness: boolean,
      hasSpecialDiagnostics: boolean,
      childSymptoms: Symptom[]
    ) {
      this.id = id;
      this.name = name;
      this.isAMentalIllness = isAMentalIllness;
      this.hasSpecialDiagnostics = hasSpecialDiagnostics;
      this.childSymptoms = childSymptoms;
    }
  }
  