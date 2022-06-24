import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionVariables } from 'src/app/common/SessionVariables';
import { User } from 'src/app/models/User';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register-normal',
  templateUrl: './register-normal.component.html',
  styleUrls: ['./register-normal.component.css']
})
export class RegisterNormalComponent implements OnInit {



  public loginError:string="";

  public signup_firstname:string="";
  public signup_lastname:string="";
  public signup_email:string="";
  public signup_password:string="";
  public signup_password2:string="";
  public signup_phone:string="";
  ngOnInit(): void {
  }

  constructor(private router:Router,private userService:UserService){}

  
  signup(){
    if(this.signup_password===this.signup_password2){
    var u:User={

     firstname:this.signup_firstname,
     lastname:this.signup_lastname,
     email:this.signup_email,
     password:this.signup_password,
     phone:this.signup_phone,
     
   };

   this.userService.add(u).subscribe(resp=>{
       this.login();
   },error=>{
     console.log(error);
     this.loginError='Erreur lors de l\'inscription';
   });;
  }
  else{
    this.loginError="password missmatch."
  }
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
      
      this.router.navigateByUrl("/");
    },
    error=>{

    });
    

  },
  error=>{
    this.loginError="Login or password incorrect";
  }
  );
}

}
