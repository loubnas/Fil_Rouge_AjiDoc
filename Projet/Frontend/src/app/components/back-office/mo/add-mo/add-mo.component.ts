import { Component, Input, OnInit } from '@angular/core';
import { MedicalOffice } from 'src/app/models/MedicalOffice';
import { ListeMoComponent } from '../liste-mo/liste-mo.component';

@Component({
  selector: 'app-add-mo',
  templateUrl: './add-mo.component.html',
  styleUrls: ['./add-mo.component.css']
})
export class AddMoComponent implements OnInit {

  @Input() MO!:MedicalOffice;
  @Input() list!:ListeMoComponent;

  ngOnInit(): void {
  }
  add(){
    this.list.add(this.MO);
  }

}
