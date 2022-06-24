import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionVariables } from '../common/SessionVariables';
import { MedicalOfficeStaff } from '../models/MedicalOfficeStaff';

@Injectable({
  providedIn: 'root'
})
export class MedicalOfficeStaffService {
  
  constructor(private http: HttpClient) { }

  url=SessionVariables.serverUrl+'/medicalOfficeStaff';

  getAll(){
      return this.http.get<MedicalOfficeStaff[]>(`${this.url}`,{headers:{"Authorization":SessionVariables.connectionToken}});
  }
  
  getById(id:number){
      return this.http.get<MedicalOfficeStaff>(`${this.url}/${id}`,{headers:{"Authorization":SessionVariables.connectionToken}});
  }

  
  add(medicalOfficeStaff:MedicalOfficeStaff){
      return this.http.post(`${this.url}/save`, medicalOfficeStaff,{headers:{"Authorization":SessionVariables.connectionToken}});
  }

  update(medicalOfficeStaff:MedicalOfficeStaff){
      return this.http.put(`${this.url}/update`, medicalOfficeStaff,{headers:{"Authorization":SessionVariables.connectionToken}});
  }
  
  delete(id:number){
    return this.http.delete(`${this.url}/delete/${id}`,{headers:{"Authorization":SessionVariables.connectionToken}});
}


}
