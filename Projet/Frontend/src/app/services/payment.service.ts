import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionVariables } from '../common/SessionVariables';
import { Payment } from '../models/Payment';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http: HttpClient) { }

  url=SessionVariables.serverUrl+'/payment';

  getAll(){
      return this.http.get<Payment[]>(`${this.url}`,{headers:{"Authorization":SessionVariables.connectionToken}});
  }
  
  getById(id:number){
      return this.http.get<Payment>(`${this.url}/${id}`,{headers:{"Authorization":SessionVariables.connectionToken}});
  }

  
  add(payment:Payment){
      return this.http.post(`${this.url}/save`, payment,{headers:{"Authorization":SessionVariables.connectionToken}});
  }

  update(payment:Payment){
      return this.http.put(`${this.url}/update`, payment,{headers:{"Authorization":SessionVariables.connectionToken}});
  }
  
  delete(id:number){
      return this.http.delete(`${this.url}/delete/${id}`,{headers:{"Authorization":SessionVariables.connectionToken}});
  }
}
