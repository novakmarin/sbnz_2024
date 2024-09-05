package com.ftn.sbnz.model.models;

public class ClassificationTemplateModel {
	private String symptomName;
	private int minSymptomsPresent;
	
	public ClassificationTemplateModel(String symptomName, int minSymptomsPresent) {
		super();
		this.symptomName = symptomName;
		this.minSymptomsPresent = minSymptomsPresent;
	}

	public String getSymptomName() {
		return symptomName;
	}

	public void setSymptomName(String symptomName) {
		this.symptomName = symptomName;
	}

	public int getMinSymptomsPresent() {
		return minSymptomsPresent;
	}

	public void setMinSymptomsPresent(int minSymptomsPresent) {
		this.minSymptomsPresent = minSymptomsPresent;
	}
	
	
}
