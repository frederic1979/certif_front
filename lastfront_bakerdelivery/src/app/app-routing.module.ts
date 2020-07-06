import {RouterModule, Routes} from '@angular/router';
import {WelcomePageComponent} from './component/welcome-page/welcome-page.component';
import {NgModule} from '@angular/core';
import {AdminGuard} from './authentification/guards/admin.guard';
import {TopBarComponent} from './component/top-bar/top-bar.component';
import {RestaurantDetailComponent} from './component/restaurant-detail/restaurant-detail.component';
import {CreationMatrixComponent} from './component/creation-matrix/creation-matrix.component';
import {DeliveryOnboardComponent} from './component/delivery-onboard/delivery-onboard.component';
import {FooterComponent} from './component/footer/footer.component';
import {LoginComponent} from './component/login/login.component';
import {RestaurantListComponent} from './component/restaurant-list/restaurant-list.component';
import {SellerGuard} from './authentification/guards/seller.guard';


const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'restaurant-list', component: RestaurantListComponent, canActivate: [AdminGuard,SellerGuard]},
  {path: 'creation-matrix', component: CreationMatrixComponent, canActivate: [AdminGuard]},

  {path: 'restaurants/week/:id/:start/:end', component: RestaurantListComponent},
  {path: 'restaurants/compteur/:restaurantId/:date', component: RestaurantDetailComponent},
  {path: 'onboard/:date', component: DeliveryOnboardComponent},
  {path: 'matrix/restaurant/:restaurantId', component: CreationMatrixComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
