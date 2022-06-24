import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionVariables } from '../common/SessionVariables';
import { MedicalOffice } from '../models/MedicalOffice';

@Injectable({
  providedIn: 'root'
})
export class MedicalOfficeService {

  constructor(private http: HttpClient) { }

  url=SessionVariables.serverUrl+'/medicalOffice';

  getAll(){
      return this.http.get<MedicalOffice[]>(`${this.url}`,{headers:{"Authorization":SessionVariables.connectionToken}});
  }
  
  getById(id:number){
      return this.http.get<MedicalOffice>(`${this.url}/${id}`,{headers:{"Authorization":SessionVariables.connectionToken}});
  }

  
  add(medicalOffice:MedicalOffice){
      return this.http.post(`${this.url}/save`, medicalOffice,{headers:{"Authorization":SessionVariables.connectionToken}});
  }

  update(medicalOffice:MedicalOffice){
      return this.http.put(`${this.url}/update`, medicalOffice,{headers:{"Authorization":SessionVariables.connectionToken}});
  }
  
  delete(id:number){
      return this.http.delete(`${this.url}/delete/${id}`,{headers:{"Authorization":SessionVariables.connectionToken}});
  }
  
search(keyword:String,page:Number,limit:Number){
    return this.http.get<any>(`${this.url}/search?keyword=${keyword}&page=${page}&limit=${limit}`);
}
}
