export interface Icommand {
  id: number;
  quantity: number;
  date: string;
  etat: string;
  restaurantId: number;
}

export class Command implements Icommand {
  id: number;
  quantity: number;
  date: string;
  etat: string;
  restaurantId: number;


}
