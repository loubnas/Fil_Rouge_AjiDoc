import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/models/User';
import { ListeSecretaireComponent } from '../liste-secretaire/liste-secretaire.component';

@Component({
  selector: 'app-delete-secretaire',
  templateUrl: './delete-secretaire.component.html',
  styleUrls: ['./delete-secretaire.component.css']
})
export class DeleteSecretaireComponent implements OnInit {

  constructor() { }
  @Input() user!:User;
  @Input() list!:ListeSecretaireComponent;

  ngOnInit(): void {
  }
  delete(){
    console.log("del");
    this.list.delete(this.user);
  }
}
