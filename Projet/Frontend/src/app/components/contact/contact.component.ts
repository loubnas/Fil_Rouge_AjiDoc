import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Message } from 'src/app/models/Message';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {


  public name:string="";
  public phone:string="";
  public message:string="";
  public result:string="";
  public error:string="";

  constructor(public router:Router,private messageService:MessageService) { }

  ngOnInit(): void {
  }

  sendMessage(){
    var message:Message={
      name:this.name,
      phone:this.phone,
      message:this.message
    }
    this.messageService.add(message).subscribe(data=>{
      this.result="Votre message est bien envoyer.";
      this.name="";
      this.phone="";
      this.message="";
      
    },
    error=>{
      this.error="Erreur lors de l'envoi de votre message";
    })
  }

}
