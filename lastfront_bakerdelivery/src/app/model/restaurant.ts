export interface Irestaurant {
  id: number;
  name: string;
  email: string;
  adresse: string;
}

export class Restaurant implements Irestaurant {
  id: number;
  name: string;
  email: string;
  adresse: string;
}
