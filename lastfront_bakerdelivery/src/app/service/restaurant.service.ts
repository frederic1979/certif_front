import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';
import {Restaurant} from '../model/restaurant';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  constructor(private httpClient: HttpClient) {
  }


  getRestaurantById(restaurantId): Observable<Restaurant> {
    return this.httpClient.get<Restaurant>('http://localhost:8080/api/bakerdelivery/restaurants/' + restaurantId);
  }

  getRestaurantList(): Observable<Restaurant[]> {
    return this.httpClient.get<Restaurant[]>('http://localhost:8080/api/bakerdelivery/restaurants/');
  }


}
