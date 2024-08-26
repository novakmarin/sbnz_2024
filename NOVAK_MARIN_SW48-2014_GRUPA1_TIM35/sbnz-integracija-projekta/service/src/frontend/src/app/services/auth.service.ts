import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginDTO } from '../model/loginDTO';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) { }

  login(username: string, password: string) {
    const loginDTO = new LoginDTO(username, password);
    const param = JSON.stringify(loginDTO);
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    console.log(param);
    return this.http.post("http://localhost:8080/login", param, { headers: headers });
  }
    
  logout(){
        localStorage.removeItem("currentUser");  
  }
}
