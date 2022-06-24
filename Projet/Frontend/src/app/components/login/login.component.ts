import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SessionVariables } from 'src/app/common/SessionVariables';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginError:string="";
  public email:string="";
  public password:string="";
  ngOnInit(): void {
  }

  constructor(private router:Router,private userService:UserService){}
  
  login(){
    this.userService.login(this.email,this.password).subscribe(resp=>{
      console.log(resp);
      SessionVariables.connectionToken=resp.headers.get("Authorization")!;
      const userId= parseInt( resp.headers.get("UserId")!);
    console.log("userid",userId);
    this.userService.getById(userId).subscribe(data=>{
    
      SessionVariables.connectedUser=data;
      SessionVariables.updateSessionVariables();
      if(data.userExtraInfo){
        
      this.router.navigateByUrl("/dashboard");
      }else{
        
      this.router.navigateByUrl("/");
      }
    },
    error=>{

    });
      
      
    this.router.navigateByUrl("/");
    //alert("log")

    },
    error=>{
      this.loginError="Login or password incorrect";
    }
    );
  }
}
