import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Command} from '../model/command';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CommandService {

  constructor(private httpClient: HttpClient) { }


  getCommandByRestaurantIdAndDate(restaurantId, date): Observable<Command> {
    return this.httpClient.get<Command>
    ('http://localhost:8080/api/bakerdelivery/commands/restaurant/' + restaurantId + '/' + date);
  }

  getCommandsByRestaurantIdAndBetweenTwoDates(restaurantId, start, end): Observable<Command[]> {
    // tslint:disable-next-line:max-line-length
    return this.httpClient.get<Command[]>('http://localhost:8080/api/bakerdelivery/commands/restaurant/' + restaurantId + '/datesbetween?start=' + start + '&end=' + end);
  }


  getCommandsByEtatAndDate(etat, date): Observable<Command[]> {
    return this.httpClient.get<Command[]>('http://localhost:8080/api/bakerdelivery/commands/etat/' + etat + '/' + date);
  }

  getCommandsBetweenTwoDates(id, start, end): Observable<Command[]> {
    return this.httpClient.get<Command[]>('http://localhost:8080/api/bakerdelivery/commands/week/' + id + '/' + start + '/' + end);
  }

  getCommandsByDate(date): Observable<Command[]> {
    return this.httpClient.get<Command[]>('http://localhost:8080/api/bakerdelivery/commands/date/' + date);
  }

  addCommand(command): Observable<Command> {
    return this.httpClient.post<Command>('http://localhost:8080/api/bakerdelivery/commands/', command);
  }

  updateCommand(command, commandId): Observable<Command> {
    return this.httpClient.put<Command>('http://localhost:8080/api/bakerdelivery/commands/' + commandId, command);
  }

  deleteCommand(commandId): Observable<Command> {
    return this.httpClient.delete<Command>('http://localhost:8080/api/bakerdelivery/commands/' + commandId);
  }


}



