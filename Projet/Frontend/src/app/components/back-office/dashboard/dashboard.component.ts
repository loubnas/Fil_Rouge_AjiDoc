import { Component, OnInit } from '@angular/core';
import { SessionVariables } from 'src/app/common/SessionVariables';

@Component({
  selector: 'app-mo-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class MODashboardComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    SessionVariables.getSessionVariables();
    console.log(SessionVariables.connectedUser);
  }

}
