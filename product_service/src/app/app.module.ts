import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';

import { ProdListingComponent } from './prod-listing/prod-listing.component';
import { FormsModule } from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { AuthComponent } from './auth/auth.component';
import { HomeComponent } from './home/home.component';
import { HttpService } from './services/http.service';
import { AuthService } from './services/auth.service';
import { LoginComponent } from './login/login.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    ProdListingComponent,
    AuthComponent,
    HomeComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [HttpService, AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
