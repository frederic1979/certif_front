export interface Imatrix {
  id: number;

  day: number;
  quantity: number;

  startDate: string;

  endDate: string;

  restaurantId: number;
}

export class Matrix implements Imatrix {
  id: number;
  day: number;
  quantity: number;


  startDate: string;

  endDate: string;

  restaurantId: number;


}
