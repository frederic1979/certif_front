import { Component, OnInit } from '@angular/core';
import {MatrixService} from '../../service/matrix.service';
import {CommandService} from '../../service/command.service';
import {RestaurantService} from '../../service/restaurant.service';
import {Restaurant} from '../../model/restaurant';
import {ActivatedRoute} from '@angular/router';
import {Matrix} from '../../model/matrix';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import * as moment from 'moment';

@Component({
  selector: 'app-creation-matrix',
  templateUrl: './creation-matrix.component.html',
  styleUrls: ['./creation-matrix.component.css']
})
export class CreationMatrixComponent implements OnInit {

  matrixList: Matrix[];
  restaurantId;
  managementForm: FormGroup;
  startDateForm: FormControl;
  isSubmitted: boolean;
  todayDate = moment().format('YYYY-MM-DD');
  matrixDejaCree: Matrix;
  newMatrix: Matrix = new Matrix();

  constructor(private restaurantService: RestaurantService, private commandService: CommandService, private matrixService: MatrixService, private route: ActivatedRoute, private formBuilder: FormBuilder) {
  }

  ngOnInit() {

    this.route.paramMap.subscribe(params => {
      /*On top la date de URL*/
      this.restaurantId = params.get('restaurantId');

      this.startDateForm = new FormControl('');


      this.matrixService.getMatrixByRestaurantId(this.restaurantId).subscribe(list =>
        this.matrixList = list);


    });

  }



  setDayOfTheWeek(numberOfDay) {
    switch (numberOfDay) {

      case 0 :
        return 'Lundi';
        break;
      case 1 :
        return 'Mardi';
        break;
      case 2 :
        return 'Mercredi';
        break;
      case 3 :
        return 'Jeudi';
        break;
      case 4 :
        return 'Vendredi';
        break;
      case 5 :
        return 'Samedi';
        break;
      case 6 :
        return 'Dimanche';
        break;

    }

  }

  addQuantity(matrix: Matrix, addOrSub: number) {
    matrix.quantity = matrix.quantity + addOrSub;

  }


  createMatrix(startDateForm, matrix) {
    this.newMatrix.day = matrix.day;
    this.newMatrix.quantity = matrix.quantity;
    this.newMatrix.startDate = startDateForm;
    this.newMatrix.endDate = '';
    this.newMatrix.restaurantId = this.restaurantId;


    this.matrixService.createMatrix(this.newMatrix).subscribe(rep => {
     this.newMatrix=rep;
    });

  }




}
