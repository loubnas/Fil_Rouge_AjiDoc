import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionVariables } from '../common/SessionVariables';
import { Appointement } from '../models/Appointement';

@Injectable({
  providedIn: 'root'
})
export class AppointementService {

  constructor(private http: HttpClient) { }

  url=SessionVariables.serverUrl+'/appointement';

  getAll(){
      return this.http.get<Appointement[]>(`${this.url}`,{headers:{"Authorization":SessionVariables.connectionToken}});
  }
  
  getById(id:number){
    return this.http.get<Appointement>(`${this.url}/${id}`,{headers:{"Authorization":SessionVariables.connectionToken}});
}

getByUserId(id:number){
    return this.http.get<Appointement[]>(`${this.url}/user/${id}`,{headers:{"Authorization":SessionVariables.connectionToken}});
}

  
  add(appointement:Appointement){
      return this.http.post<Appointement>(`${this.url}/save`, appointement,{headers:{"Authorization":SessionVariables.connectionToken}});
  }

  update(appointement:Appointement){
      return this.http.put(`${this.url}/update`, appointement,{headers:{"Authorization":SessionVariables.connectionToken}});
  }
  
  delete(id:number){
      return this.http.delete(`${this.url}/delete/${id}`,{headers:{"Authorization":SessionVariables.connectionToken}});
  }
}
