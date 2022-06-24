import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { FooterComponent } from './components/footer/footer.component';
import { ProfilDoctorComponent } from './components/profil-doctor/profil-doctor.component';
import { SearchDoctorComponent } from './components/search-doctor/search-doctor.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { RegisterNormalComponent } from './components/register-normal/register-normal.component';
import { RegisterMOComponent } from './components/register-mo/register-mo.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { MakeAppointementComponent } from './components/make-appointement/make-appointement.component';
import { SimpleAppointementComponent } from './components/simple-appointement/simple-appointement.component';
import { AdvancedAppointementComponent } from './components/advanced-appointement/advanced-appointement.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDatepicker, MatDatepickerInput, MatDatepickerModule} from '@angular/material/datepicker'
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatNativeDateModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NgxPayPalModule } from 'ngx-paypal';
import { AboutComponent } from './components/about/about.component';
import { ContactComponent } from './components/contact/contact.component';
import { ProfileComponent } from './components/profile/profile.component';
import { MODashboardComponent } from './components/back-office/dashboard/dashboard.component';
import { SidbarComponent } from './components/back-office/sidbar/sidbar.component';
import { AddSecretaireComponent } from './components/back-office/secretaire/add-secretaire/add-secretaire.component';
import { UpdateSecretaireComponent } from './components/back-office/secretaire/update-secretaire/update-secretaire.component';
import { DeleteSecretaireComponent } from './components/back-office/secretaire/delete-secretaire/delete-secretaire.component';
import { ListeSecretaireComponent } from './components/back-office/secretaire/liste-secretaire/liste-secretaire.component';
import { AddMoComponent } from './components/back-office/mo/add-mo/add-mo.component';
import { UpdateMoComponent } from './components/back-office/mo/update-mo/update-mo.component';
import { DeleteMoComponent } from './components/back-office/mo/delete-mo/delete-mo.component';
import { ListeMoComponent } from './components/back-office/mo/liste-mo/liste-mo.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FooterComponent,
    ProfilDoctorComponent,
    SearchDoctorComponent,
    LoginComponent,
    RegisterNormalComponent,
    RegisterMOComponent,
    MakeAppointementComponent,
    SimpleAppointementComponent,
    AdvancedAppointementComponent,
    NavbarComponent,
    AboutComponent,
    ContactComponent,
    ProfileComponent,
    MODashboardComponent,
    SidbarComponent,
    AddSecretaireComponent,
    UpdateSecretaireComponent,
    DeleteSecretaireComponent,
    ListeSecretaireComponent,
    AddMoComponent,
    UpdateMoComponent,
    DeleteMoComponent,
    ListeMoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxPaginationModule,
    BrowserAnimationsModule,
  NgxPayPalModule

  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
