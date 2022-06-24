import { Component, Input, OnInit } from '@angular/core';
import { MedicalOffice } from 'src/app/models/MedicalOffice';
import { ListeMoComponent } from '../liste-mo/liste-mo.component';

@Component({
  selector: 'app-delete-mo',
  templateUrl: './delete-mo.component.html',
  styleUrls: ['./delete-mo.component.css']
})
export class DeleteMoComponent implements OnInit {

  @Input() MO!:MedicalOffice;
  @Input() list!:ListeMoComponent;

  ngOnInit(): void {
  }
  delete(){
    this.list.delete(this.MO);
  }

}
