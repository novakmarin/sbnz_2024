import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { NewRuleTemplateModel } from '../model/newRuleTemplateModel';

@Injectable({
  providedIn: 'root'
})
export class NrtmService {

  private apiUrl = 'http://localhost:8080/ruleTemplateModels';

  constructor(private http: HttpClient) { }

  createNrtm(nrtm: NewRuleTemplateModel): Observable<any> {
    return this.http.post(this.apiUrl, nrtm, { observe: 'response' });
  }
}
