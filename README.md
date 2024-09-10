# SAPPUD - Sistem za asistenciju psihijatrima prilikom uspostavljanja dijagnoza
SW-48/2014, 
Novak Marin, 
Tim 35

## Uputstvo za pokretanje
- Aplikacija koristi Angular 18 za frontend. Link sa uputstvom za instalaciju Angulara 18: https://angular.dev/installation
- Klonirati projekat
### Backend:
- Pozvati maven clean install i maven update project nad sljedecim aplikacijama: model, kjar i service, respektivnim redoslijedom.
- Pokretanje backenda -> ServiceApplication, Run As: Java application
- Ukoliko se baza podataka ne popuni automatski, izvršiti sadržaj data.sql datoteke iz resources foldera service aplikacije nad sbnz_2024 šemom baze podataka u MySQL-u
### Frontend:
- Pozicionirati se u frontend projekat
- Instalirati sljedeće zavisnosti koristeći navedene komande:
- PrimeNG: npm install primeng --save
- PrimeIcons: npm install primeicons --save
- Angular Material: ng add @angular/material
- Pokrenuti frontend aplikaciju komandom ng serve
