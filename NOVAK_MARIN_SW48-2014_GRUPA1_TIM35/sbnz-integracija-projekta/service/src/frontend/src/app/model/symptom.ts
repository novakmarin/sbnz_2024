export class Symptom {
    id: number;
    code: string;
    name: string;
    amentalIllness: boolean;
    hasSpecialDiagnostics: boolean;
    isCustomSymptom: boolean;
    childSymptoms: Symptom[];
  
    constructor(
      id: number,
      code: string,
      name: string,
      amentalIllness: boolean,
      hasSpecialDiagnostics: boolean,
      isCustomSymptom: boolean,
      childSymptoms: Symptom[]
    ) {
      this.id = id;
      this.code = code;
      this.name = name;
      this.amentalIllness = amentalIllness;
      this.hasSpecialDiagnostics = hasSpecialDiagnostics;
      this.isCustomSymptom = isCustomSymptom;
      this.childSymptoms = childSymptoms;
    }
  }
  