import { Component, OnInit } from '@angular/core';
import { SessionVariables } from 'src/app/common/SessionVariables';

@Component({
  selector: 'app-make-appointement',
  templateUrl: './make-appointement.component.html',
  styleUrls: ['./make-appointement.component.css']
})
export class MakeAppointementComponent implements OnInit {

  constructor() { }
connectedUser:any=null;
  ngOnInit(): void {
    
    SessionVariables.getSessionVariables();
    this.connectedUser=SessionVariables.connectedUser;
  }

}
