import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import {RouterModule} from '@angular/router';
import { WelcomePageComponent } from './component/welcome-page/welcome-page.component';
import { RestaurantListComponent } from './component/restaurant-list/restaurant-list.component';
import { TopBarComponent } from './component/top-bar/top-bar.component';
import { FooterComponent } from './component/footer/footer.component';
import {HttpClientModule} from '@angular/common/http';
import {RestaurantDetailComponent} from './component/restaurant-detail/restaurant-detail.component';
import {ReactiveFormsModule} from '@angular/forms';
import { DeliveryOnboardComponent } from './component/delivery-onboard/delivery-onboard.component';


import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { CreationMatrixComponent } from './component/creation-matrix/creation-matrix.component';
import {LayoutModule} from '@angular/cdk/layout';

import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MatInputModule} from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import {MatRadioModule} from '@angular/material/radio';
import {MatCardModule} from '@angular/material/card';
import {MatTableModule} from "@angular/material/table";
import {AdminGuard} from './authentification/guards/admin.guard';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {JwtInterceptor} from './authentification/http-interceptor/jwt.interceptor';
import { LoginComponent } from './component/login/login.component';
import {AppRoutingModule} from './app-routing.module';
import {MatRippleModule} from '@angular/material/core';
import {SellerGuard} from './authentification/guards/seller.guard';



@NgModule({
  declarations: [
    AppComponent,
    WelcomePageComponent,
    RestaurantListComponent,
    TopBarComponent,
    FooterComponent,
    RestaurantDetailComponent,
    DeliveryOnboardComponent,
    CreationMatrixComponent,
    LoginComponent,


  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatInputModule,
    MatSelectModule,
    MatRadioModule,
    MatCardModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatTableModule,
    AppRoutingModule,
    MatRippleModule,

  ],
  providers: [ AdminGuard, SellerGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
