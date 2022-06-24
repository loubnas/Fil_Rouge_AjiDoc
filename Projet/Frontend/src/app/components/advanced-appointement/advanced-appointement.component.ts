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
import { IPayPalConfig, ICreateOrderRequest } from 'ngx-paypal';
import { PaymentService } from 'src/app/services/payment.service';
import { Payment } from 'src/app/models/Payment';

@Component({
  selector: 'app-advanced-appointement',
  templateUrl: './advanced-appointement.component.html',
  styleUrls: ['./advanced-appointement.component.css'],
})
export class AdvancedAppointementComponent implements OnInit, AfterViewInit {
  public payPalConfig?: IPayPalConfig;
  constructor(
    private fb: FormBuilder,
    private router: Router,
    private appointementService: AppointementService,
    private location: Location,
    private paymentService:PaymentService
  ) {}
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
  public dateError: boolean = false;
  public showSuccess: boolean = false;
  public isPayed: boolean = false;
  public btnshow: boolean = true;

  public fg!: FormGroup;

  @ViewChild('datePicker') datePicker: ElementRef | undefined;
  ngOnInit(): void {
    SessionVariables.getSessionVariables();
    this.selectedMO = SessionVariables.selectedMO;
    this.fg = this.fb.group({
      date: [null, [Validators.required]],
      time: [null, [Validators.required]],
    });
    this.initPaypalConfig();
  }
  changeDate(evt: any) {
    document
      .getElementById('datePicker')
      ?.setAttribute('value', this.datePicker?.nativeElement.value);
  }

  makeAppointement(event:any) {
    event.stopPropagation();
    if (this.appointement == null) {
      this.fg.controls['date'].setValue(this.datePicker?.nativeElement.value);
      
      var y =new Date(this.datePicker?.nativeElement.value);// MM-DD-YYYY
      y.setHours(0);
      y.setMinutes(0);
      y.setSeconds(0);
      y.setMilliseconds(0);
      var x=new Date();
      
      x.setHours(0);
      x.setMinutes(0);
      x.setSeconds(0);
      x.setMilliseconds(0);
      x.setDate(x.getDate()+15);
      
      this.rvDate=x.getDate()+'/'+(x.getMonth()+1)+'/'+x.getFullYear();
      
      if(y>=x){
        this.dateError=false;
      
      
      console.log(this.fg.valid);
      if (this.fg.valid) {
        var appointement: Appointement = {
          dateTimeAppointement:
            this.datePicker?.nativeElement.value + ' ' + this.rvTime,
          userId: SessionVariables.connectedUser?.id,
          medicalOfficeId: SessionVariables.selectedMO?.id,
        };
        this.appointementService.add(appointement).subscribe(
          (data) => {
            console.log(data);
            this.appointement = data;
            this.btn = 'retourner';
            this.btnshow=false;
            this.msg = 'votre demande a bien été ajouter - veuillez payer pour securisé votre rendez-vous';
            
          },
          (error) => {
            this.error = true;
            this.appointement = {};
            this.btn = 'Demandez à nouveau un rendez-vous';
            this.btnshow=true;
            this.msg = "erreur lord de l'ajout de votre demande";
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
    }else{
this.dateError=true;
    }
    } else {
      this.location.back();
    }
  }


  private initPaypalConfig(): void {
    this.payPalConfig = {
    currency: 'EUR',
    clientId: 'AfxuIy6jzQACDOZ1j-UviUPMk6KR1AApzvvgUn4prZfHNrtLaD5MK9f1eFivs15XHGSKkR3dx0ldE8Xi',
    
  
    createOrderOnClient: (data) => <ICreateOrderRequest>{
      intent: 'CAPTURE',
      purchase_units: [
        {
          amount: {
            currency_code: 'EUR',
            value: '20.00',
            breakdown: {
              item_total: {
                currency_code: 'EUR',
                value: '20.00'
              }
            }
          },
          items: [
            {
              name: 'Doctor Appointement',
              quantity: '1',
              category: 'DIGITAL_GOODS',
              unit_amount: {
                currency_code: 'EUR',
                value: '20.00',
              },
            }
          ]
        }
      ]
    },
    advanced: {
      commit: 'true',
    },
    style: {
      label: 'paypal',
      layout: 'vertical'
    },
    onApprove: (data, actions) => {
      console.log('onApprove - transaction was approved, but not authorized', data, actions);
      
      actions.order.get().then((details: any) => {
        console.log('onApprove - you can get full order details inside onApprove: ', details);
        var appointement: Appointement = {
          dateTimeAppointement:
            this.datePicker?.nativeElement.value + ' ' + this.rvTime,
          userId: SessionVariables.connectedUser?.id,
          medicalOfficeId: SessionVariables.selectedMO?.id,
        };

      
            var payment:Payment={
              online:true,
              onlineReference:JSON.stringify(details),
              appointement:{id:this.appointement?.id}
            }
            console.log(payment)
            this.paymentService.add(payment).subscribe(data=>{
              
            this.appointement = data;
            this.btn = 'retourner';
            this.btnshow=true;
            this.msg = 'votre rendez-vous est enregistrer et payée. merci pour votre confiance';
            this.isPayed=true;
            },error=>{
              this.error=true;
            this.msg = 'erreur lors de l\'enregistrement de votre paiement';
            this.btnshow=false;
            });
          
      });
    },
    onClientAuthorization: (data) => {
      console.log('onClientAuthorization - you should probably inform your server about completed transaction at this point', data);
      this.showSuccess = true;
    },
    onCancel: (data, actions) => {
      console.log('OnCancel', data, actions);
    },
    onError: err => {
      console.log('OnError', err);
    },
    onClick: (data, actions) => {
      console.log('onClick', data, actions);
    },
  };
  }
}

