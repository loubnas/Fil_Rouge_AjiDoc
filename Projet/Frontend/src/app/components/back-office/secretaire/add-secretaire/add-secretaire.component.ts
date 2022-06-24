import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MedicalOffice } from 'src/app/models/MedicalOffice';
import { User } from 'src/app/models/User';
import { ListeSecretaireComponent } from '../liste-secretaire/liste-secretaire.component';

@Component({
  selector: 'app-add-secretaire',
  templateUrl: './add-secretaire.component.html',
  styleUrls: ['./add-secretaire.component.css']
})
export class AddSecretaireComponent implements OnInit {

  constructor(private route: Router) { }

  @Input() user!:User;
  @Input() MOs!:MedicalOffice[];
  @Input() list!:ListeSecretaireComponent;
  

  ngOnInit(): void {
  }
  add(){
    this.list.add(this.user);
  }


}
