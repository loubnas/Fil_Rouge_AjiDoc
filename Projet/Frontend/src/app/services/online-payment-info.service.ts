import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionVariables } from '../common/SessionVariables';
import { OnlinePayementInfo } from '../models/OnlinePaymentInfo';

@Injectable({
  providedIn: 'root'
})
export class OnlinePaymentInfoService {
  constructor(private http: HttpClient) { }

  url=SessionVariables.serverUrl+'/onlinePaymentInfo/';

  getAll(){
      return this.http.get<OnlinePayementInfo[]>(`${this.url}`,{headers:{"Authorization":SessionVariables.connectionToken}});
  }
  
  getById(id:number){
      return this.http.get<OnlinePayementInfo>(`${this.url}/${id}`,{headers:{"Authorization":SessionVariables.connectionToken}});
  }

  
  add(onlinePayementInfo:OnlinePayementInfo){
      return this.http.post(`${this.url}/save`, onlinePayementInfo,{headers:{"Authorization":SessionVariables.connectionToken}});
  }

  update(onlinePayementInfo:OnlinePayementInfo){
      return this.http.put(`${this.url}/update`, onlinePayementInfo,{headers:{"Authorization":SessionVariables.connectionToken}});
  }
  
  delete(id:number){
      return this.http.delete(`${this.url}/delete/${id}`,{headers:{"Authorization":SessionVariables.connectionToken}});
  }
}
