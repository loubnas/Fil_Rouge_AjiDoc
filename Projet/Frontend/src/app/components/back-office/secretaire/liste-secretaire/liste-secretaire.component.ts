import { AfterContentInit, Component,  OnInit,  } from '@angular/core';
import { SessionVariables } from 'src/app/common/SessionVariables';
import { User } from 'src/app/models/User';
import { MedicalOffice } from 'src/app/models/MedicalOffice';
import { UserDocumentService } from 'src/app/services/user-document.service';
import { UserService } from 'src/app/services/user.service';
import { MedicalOfficeStaffService } from 'src/app/services/medical-office-staff.service';
import { MedicalOfficeStaff } from 'src/app/models/MedicalOfficeStaff';
import { Router } from '@angular/router';



@Component({
  selector: 'app-liste-secretaire',
  templateUrl: './liste-secretaire.component.html',
  styleUrls: ['./liste-secretaire.component.css']
})
export class ListeSecretaireComponent implements OnInit,AfterContentInit {

  public openAdd : boolean = false;
  public openUpdate : boolean = false;
  public openDelete : boolean = false;
  public staff:User[]=[];
  public MOs:MedicalOffice[]=[];
  public selectedStaff:User={};

  public addModal:any;
  public updateModal:any;
  public deleteModal:any;

  constructor(private router:Router,private userService:UserService,private staffService:MedicalOfficeStaffService) { }
  ngAfterContentInit(): void {
    this.addModal = document.getElementById('add-modal');
    this.updateModal = document.getElementById('add-modal');
    this.deleteModal = document.getElementById('add-modal');
  }

  update(user:User){
    this.userService.update(user).subscribe(data=>{
      var ms:MedicalOfficeStaff={
        id:data.id,
        medicalOfficeStaffUser:{id:data.id},
        medicalOfficeId:user.medicalOfficeId
      }
      this.staffService.update(ms).subscribe(data=>{
        //update
       // window.location.reload();
       this.init();
      })
    })
  }
  add(user:User){
    this.userService.add(user).subscribe(data=>{
      var ms:MedicalOfficeStaff={
        id:data.id,
        medicalOfficeStaffUser:{id:data.id},
        medicalOfficeId:user.medicalOfficeId
      }
      this.staffService.add(ms).subscribe(data=>{
        //update
        this.userService.getById(SessionVariables.connectedUser?.id).subscribe(data=>{
    
          SessionVariables.connectedUser=data;
          SessionVariables.updateSessionVariables();
          this.init();

        });
        
      })
    })
  }

  delete(user:User){
    var ms:MedicalOfficeStaff={
      id:user.id,
      medicalOfficeStaffUser:user,
    }
    console.log(user);
    this.staffService.delete(user.id!).subscribe(data=>{
    console.log("del",data)
      this.userService.delete(user.id!).subscribe(data=>{
        //update
        this.userService.getById(SessionVariables.connectedUser?.id).subscribe(data=>{
    
          SessionVariables.connectedUser=data;
          SessionVariables.updateSessionVariables();
         // window.location.reload();
         this.init();
        });
        
      })
    })
  }

init(){
  this.MOs=[];
  this.staff=[];
  this.MOs=SessionVariables.connectedUser?.medicalOffices;
    var mos:MedicalOffice[]=SessionVariables.connectedUser?.medicalOffices;
    mos.forEach((m)=>{
      //console.log(m.staffList)
      this.staff=this.staff.concat(m.staffList!);
    }
    );
}

  ngOnInit(): void { 
    this.init();
    //console.log(this.staff)

  }

  selectStaff(s:User){
    
    this.selectedStaff=s;
  }
  
  imageError(event : any){
    event.target.src = "data:image/png;base64,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";
  }


  

}