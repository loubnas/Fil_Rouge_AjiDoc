import { Component, Input, OnInit } from '@angular/core';
import { MedicalOffice } from 'src/app/models/MedicalOffice';
import { User } from 'src/app/models/User';
import { ListeSecretaireComponent } from '../liste-secretaire/liste-secretaire.component';

@Component({
  selector: 'app-update-secretaire',
  templateUrl: './update-secretaire.component.html',
  styleUrls: ['./update-secretaire.component.css']
})
export class UpdateSecretaireComponent implements OnInit {

  constructor() { }
  @Input() user!:User;
  @Input() MOs!:MedicalOffice[];
  @Input() list!:ListeSecretaireComponent;

  ngOnInit(): void {
  }
  update(){
    this.list.update(this.user);
  }

}
