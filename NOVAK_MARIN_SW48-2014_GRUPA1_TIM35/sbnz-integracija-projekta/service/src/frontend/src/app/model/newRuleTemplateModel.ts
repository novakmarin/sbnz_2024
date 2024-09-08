export class NewRuleTemplateModel {
    id?: number;
    symptomName?: string;
    minSymptomsPresent?: number;
  
    constructor(id?: number, symptomName?: string, minSymptomsPresent?: number) {
      this.id = id;
      this.symptomName = symptomName;
      this.minSymptomsPresent = minSymptomsPresent;
    }
  }
  