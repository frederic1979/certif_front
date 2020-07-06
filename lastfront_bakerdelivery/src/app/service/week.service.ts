import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Command} from '../model/command';
import {Week} from '../model/week';

@Injectable({
  providedIn: 'root'
})
export class WeekService {

  constructor(private httpClient: HttpClient) { }


  getWeeks(): Observable<Week[]> {
    return this.httpClient.get<Week[]>('http://localhost:8080/api/bakerdelivery/weeks/');
  }



}
