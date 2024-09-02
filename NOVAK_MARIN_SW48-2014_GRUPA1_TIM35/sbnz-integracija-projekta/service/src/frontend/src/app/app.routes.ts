import { Routes } from '@angular/router';
import { LogInComponent } from './log-in/log-in.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { AppointmentComponent } from './components/appointment/appointment.component';
import { AddPatientComponent } from './components/add-patient/add-patient.component';
import { ReportsComponent } from './components/reports/reports.component';

export const routes: Routes = [
    { path: 'login', component: LogInComponent },
    { path: '', component: LogInComponent },
    { path: 'home',  component: HomePageComponent, 
        children: [
            {path: 'appointment', component: AppointmentComponent},
            {path: 'addPatient', component: AddPatientComponent},
            {path: 'reports', component: ReportsComponent}
        ]
    },
    { path: 'appointment', component: AppointmentComponent},
];
