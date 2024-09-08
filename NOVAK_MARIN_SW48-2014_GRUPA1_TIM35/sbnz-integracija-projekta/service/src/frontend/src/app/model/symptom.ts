export class Symptom {
    id: number;
    code: string;
    name: string;
    amentalIllness: boolean;
    hasSpecialDiagnostics: boolean;
    customSymptom: boolean;
    childSymptoms: Symptom[];
  
    constructor(
      id: number,
      code: string,
      name: string,
      amentalIllness: boolean,
      hasSpecialDiagnostics: boolean,
      customSymptom: boolean,
      childSymptoms: Symptom[]
    ) {
      this.id = id;
      this.code = code;
      this.name = name;
      this.amentalIllness = amentalIllness;
      this.hasSpecialDiagnostics = hasSpecialDiagnostics;
      this.customSymptom = customSymptom;
      this.childSymptoms = childSymptoms;
    }
  }
  