import { Diagnosis } from "./diagnosis";
import { Doctor } from "./doctor";
import { Patient } from "./patient";
import { Symptom } from "./symptom";

export class Appointment{
    constructor(
        public id?: number,
        public date?: Date,
        public doctor?: Doctor,
        public patient?: Patient,
        public diagnosis?: Diagnosis,
        public currentSymptoms?: Symptom[],
        public note?: string
    ){}
}