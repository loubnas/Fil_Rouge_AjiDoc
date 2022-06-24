import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './components/about/about.component';
import { AdvancedAppointementComponent } from './components/advanced-appointement/advanced-appointement.component';
import { MODashboardComponent } from './components/back-office/dashboard/dashboard.component';
import { ListeMoComponent } from './components/back-office/mo/liste-mo/liste-mo.component';
import { ListeSecretaireComponent } from './components/back-office/secretaire/liste-secretaire/liste-secretaire.component';
import { ContactComponent } from './components/contact/contact.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { MakeAppointementComponent } from './components/make-appointement/make-appointement.component';
import { ProfilDoctorComponent } from './components/profil-doctor/profil-doctor.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterMOComponent } from './components/register-mo/register-mo.component';
import { RegisterNormalComponent } from './components/register-normal/register-normal.component';
import { SearchDoctorComponent } from './components/search-doctor/search-doctor.component';
import { SimpleAppointementComponent } from './components/simple-appointement/simple-appointement.component';


const routes: Routes = [
  {path:'',redirectTo:"home",pathMatch:"full"},
  {path:'home',component:HomeComponent},
  
  {path:'about',component:AboutComponent},
  {path:'contact',component:ContactComponent},
  {path:'profile',component:ProfileComponent},

  {path:'searchDoctor',component:SearchDoctorComponent},
  {path:'footer',component:FooterComponent},
  {path:'profilDoctor',component:ProfilDoctorComponent},
  {path:'login',component:LoginComponent},
  {path:'register',component:RegisterNormalComponent},
  {path:'registerMO',component:RegisterMOComponent},
  {path:'makeAppointement',component:MakeAppointementComponent},
  {path:'simpleAppointement',component:SimpleAppointementComponent},
  {path:'advancedAppointement',component:AdvancedAppointementComponent},
  
  {path:'dashboard',component:MODashboardComponent,
children:[
  {path:'nurse',component:ListeSecretaireComponent},
  {path:'medicalOffice',component:ListeMoComponent},
]},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
