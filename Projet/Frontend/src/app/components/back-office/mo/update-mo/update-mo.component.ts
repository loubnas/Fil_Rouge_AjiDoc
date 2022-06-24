import { Component, Input, OnInit } from '@angular/core';
import { MedicalOffice } from 'src/app/models/MedicalOffice';
import { ListeMoComponent } from '../liste-mo/liste-mo.component';

@Component({
  selector: 'app-update-mo',
  templateUrl: './update-mo.component.html',
  styleUrls: ['./update-mo.component.css']
})
export class UpdateMoComponent implements OnInit {

  constructor() { }
  @Input() MO!:MedicalOffice;
  @Input() list!:ListeMoComponent;

  ngOnInit(): void {
  }
  update(){
    this.list.update(this.MO);
  }
}
