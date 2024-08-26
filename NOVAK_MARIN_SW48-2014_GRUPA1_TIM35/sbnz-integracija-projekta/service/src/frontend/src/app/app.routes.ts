import { Routes } from '@angular/router';
import { LogInComponent } from './log-in/log-in.component';
import { HomePageComponent } from './components/home-page/home-page.component';

export const routes: Routes = [
    { path: 'login', component: LogInComponent },
    { path: '', component: LogInComponent },
    { path: 'home',  component: HomePageComponent},
];
