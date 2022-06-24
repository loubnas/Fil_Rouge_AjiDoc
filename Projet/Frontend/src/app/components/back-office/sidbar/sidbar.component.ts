import { Component, OnInit } from '@angular/core';
import { SessionVariables } from 'src/app/common/SessionVariables';

@Component({
  selector: 'app-sidbar',
  templateUrl: './sidbar.component.html',
  styleUrls: ['./sidbar.component.css']
})
export class SidbarComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }


  logout(){
    SessionVariables.clearSessionVariables();
  }
}
