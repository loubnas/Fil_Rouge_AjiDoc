import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionVariables } from 'src/app/common/SessionVariables';
import { User } from 'src/app/models/User';
import { UserExtraInfo } from 'src/app/models/UserExtraInfo';
import { UserExtraInfoService } from 'src/app/services/user-extra-info.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register-mo',
  templateUrl: './register-mo.component.html',
  styleUrls: ['./register-mo.component.css']
})
export class RegisterMOComponent implements OnInit {

  
  public loginError:string="";

  public signup_firstname:string="";
  public signup_lastname:string="";
  public signup_email:string="";
  public signup_password:string="";
  public signup_cin:string="";
  public signup_diplome:string="";
  public diplome:string="";
  public specialite:string="";
  ngOnInit(): void {
  }

  constructor(private router:Router,private userService:UserService,private userExtraInfo:UserExtraInfoService){}

  
  signup(){
    var u:User={
     firstname:this.signup_firstname,
     lastname:this.signup_lastname,
     email:this.signup_email,
     password:this.signup_password
   };

   this.userService.add(u).subscribe(resp=>{
     var extra:UserExtraInfo={
       user:{
      id:resp.id},
      specialite:this.specialite,
      diplome:this.diplome,
      cinImage:this.signup_cin,
      diplomeImage:this.signup_diplome
     }
     this.userExtraInfo.add(extra).subscribe(data=>{
      
      this.login();
     })
   },error=>{
     console.log(error);
     this.loginError='Erreur lors de l\'inscription';
   });;
 }
 login(){
  
  this.userService.login(this.signup_email,this.signup_password).subscribe(resp=>{

    SessionVariables.connectionToken=resp.headers.get("Authorization")!;
    const userId= parseInt( resp.headers.get("UserId")!);
    console.log("userid",userId);
    this.userService.getById(userId).subscribe(data=>{
      console.log(data);
      SessionVariables.connectedUser=data;
      SessionVariables.updateSessionVariables();

        this.router.navigateByUrl("/dashboard");
      
    },
    error=>{

    });
    

  },
  error=>{
    this.loginError="erreur ...";
  }
  );
}

      



}
