import { DatePipe, Location } from '@angular/common';
import {
  AfterContentInit,
  AfterViewInit,
  Component,
  ElementRef,
  OnInit,
  ViewChild,
} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDatepicker } from '@angular/material/datepicker';
import { Router } from '@angular/router';
import { SessionVariables } from 'src/app/common/SessionVariables';
import { Appointement } from 'src/app/models/Appointement';
import { AppointementService } from 'src/app/services/appointement.service';

@Component({
  selector: 'app-simple-appointement',
  templateUrl: './simple-appointement.component.html',
  styleUrls: ['./simple-appointement.component.css'],
})
export class SimpleAppointementComponent implements OnInit, AfterViewInit {
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private appointementService: AppointementService,
    private location: Location
  ) {}

  connectedUser:any=null;
  ngAfterViewInit(): void {
    var reload = localStorage.getItem('reload');
    if (!reload) {
     // window.location.reload();
      localStorage.setItem('reload', '1');
    } else {
      localStorage.removeItem('reload');
    }
  }

  public selectedMO: any = {};
  public rvDate: string = '';
  public rvTime: string = '';
  public btn = 'Demandez un rendez-vous';
  public msg = '';
  public appointement: Appointement | null = null;
  public error: boolean = false;

  public fg!: FormGroup;

  @ViewChild('datePicker') datePicker: ElementRef | undefined;

  
  ngOnInit(): void {
    SessionVariables.getSessionVariables();
    this.selectedMO = SessionVariables.selectedMO;
    this.fg = this.fb.group({
      date: [null, [Validators.required]],
      time: [null, [Validators.required]],
    });
  }
  changeDate(evt: any) {
    document.getElementById('datePicker')?.setAttribute('value', this.datePicker?.nativeElement.value);
  }

  makeAppointement(event:any) {
    event.stopPropagation();
    if (this.appointement == null) {
      this.fg.controls['date'].setValue(this.datePicker?.nativeElement.value);
      
    
      if (this.fg.valid) {
        var appointement: Appointement = {
          dateTimeAppointement:
            this.datePicker?.nativeElement.value + ' ' + this.rvTime,
          userId: SessionVariables.connectedUser?.id,
          medicalOfficeId: SessionVariables.selectedMO?.id,
        };

        console.log(appointement);

        this.appointementService.add(appointement).subscribe(
          (data) => {
            console.log(data);
            this.appointement = data;
            this.btn = 'retourner';
            this.msg = 'votre demande a bien été ajouter';
          },
          (error) => {
            console.log("error",SessionVariables.connectionToken);
            this.error = true;
            this.appointement = {};
            this.btn = 'Demandez à nouveau un rendez-vous';
            this.msg = "erreur lors de l'ajout de votre demande";
          }
        );
      } else {
        Object.values(this.fg.controls).forEach((control) => {
          if (control.invalid) {
            control.markAsDirty();
            control.updateValueAndValidity({ onlySelf: true });
          }
        });
      }
    } else {
      this.location.back();
    }
  }
}
