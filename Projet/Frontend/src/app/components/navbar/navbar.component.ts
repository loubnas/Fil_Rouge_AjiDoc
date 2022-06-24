import { AfterContentInit, Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { SessionVariables } from 'src/app/common/SessionVariables';
import { User } from 'src/app/models/User';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit ,AfterContentInit{

  constructor() { }
  getUser(){
    
    SessionVariables.getSessionVariables();
    return SessionVariables.connectedUser;
  }
  ngAfterContentInit(): void {
    
    //console.log(this.connectedUser)
  }

  ngOnInit(): void {
  }
  
  logout(){
    SessionVariables.clearSessionVariables()
    
    SessionVariables.getSessionVariables();
    //this.connectedUser=SessionVariables.connectedUser;
  }

}
