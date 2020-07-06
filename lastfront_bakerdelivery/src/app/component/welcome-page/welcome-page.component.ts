import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';

import {RestaurantService} from '../../service/restaurant.service';
import {User} from '../../model/user';
import {AuthenticationService} from '../../service/authentication.service';
import * as moment from 'moment';
import {WeekService} from '../../service/week.service';
import {Restaurant} from '../../model/restaurant';
import {Week} from '../../model/week';


@Component({
  selector: 'app-welcome-page',
  templateUrl: './welcome-page.component.html',
  styleUrls: ['./welcome-page.component.css']
})
export class WelcomePageComponent implements OnInit {
  isLoggedIn = false;
  isAdmin: boolean;
  isSeller: boolean;

  constructor(private restaurantService: RestaurantService, private weekService: WeekService, private authenticationService: AuthenticationService) { }

  restaurantList: Restaurant[] = new Array();
  todayDate = moment().format('YYYY-MM-DD');
  mondayOfTheWeek;
  mondayOfTheWeek2;
  sundayOfTheWeek;
  sundayOfTheWeek2;
  numberOfActualWeek;
  date = moment().format('YYYY-MM-DD');
  tomorrow = moment().add(1,'day').format('YYYY-MM-DD');
  weekList: Week[] = new Array();

  ngOnInit() {

    this.authenticationService.userRoles.subscribe(userRoles => {
      this.isLoggedIn = false;

      this.isAdmin = userRoles.includes('ROLE_ADMIN');
      this.isSeller = userRoles.includes('ROLE_SELLER');

      if (userRoles && userRoles.length > 0) {
        this.isLoggedIn = true;
      }
    });


    this.numberOfActualWeek = moment().format('w');
    this.findMondayOfTheWeek(this.date); // On obtient le mondayOfTheWeek
    this.findSundayOfTheWeek(this.mondayOfTheWeek); // On obtient le sundayOfTheWeek


  }

  logOut() {
    this.authenticationService.logOut();
  }

  getWeekList() {
    this.weekService.getWeeks().subscribe(
      (response) => {
        this.weekList = response;
        console.log(this.weekList.length);
      }
    );
  }


  getRestaurantList() {

    this.restaurantService.getRestaurantList().subscribe(
      (response) => {
        this.restaurantList = response;
      }
    );
  }


  findMondayOfTheWeek(date) {
    date= new Date(date);
    switch (date.getDay()) {
      case 0:

        this.mondayOfTheWeek = moment(date).add(1, 'days').format('YYYY-MM-DD');
        break;

      case 1:

        this.mondayOfTheWeek = moment(date).format('YYYY-MM-DD');
        break;

      case 2:

        this.mondayOfTheWeek = moment(date).subtract(1, 'days').format('YYYY-MM-DD');
        break;
      case 3:

        this.mondayOfTheWeek = moment(date).subtract(2, 'days').format('YYYY-MM-DD');
        break;
      case 4:

        this.mondayOfTheWeek = moment(date).subtract(3, 'days').format('YYYY-MM-DD');
        break;
      case 5:

        this.mondayOfTheWeek = moment(date).subtract(4, 'days').format('YYYY-MM-DD');
        break;
      case 6:

        this.mondayOfTheWeek = moment(date).subtract(5, 'days').format('YYYY-MM-DD');
        break;

      default:

    }
  }

  findSundayOfTheWeek(date) {
    this.sundayOfTheWeek = moment(this.mondayOfTheWeek).add(6, 'days').format('YYYY-MM-DD');
  }

}
