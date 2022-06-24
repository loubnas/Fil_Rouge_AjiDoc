import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionVariables } from '../common/SessionVariables';
import { UserDocument } from '../models/UserDocument';

@Injectable({
  providedIn: 'root'
})
export class UserDocumentService {

  constructor(private http: HttpClient) { }

  url=SessionVariables.serverUrl+'/userDoc/';

  getAll(){
      return this.http.get<UserDocument[]>(`${this.url}`,{headers:{"Authorization":SessionVariables.connectionToken}});
  }
  
  getById(id:number){
      return this.http.get<UserDocument>(`${this.url}/${id}`,{headers:{"Authorization":SessionVariables.connectionToken}});
  }

  
  add(userDocument:UserDocument){
      return this.http.post(`${this.url}/save`, userDocument,{headers:{"Authorization":SessionVariables.connectionToken}});
  }

  update(userDocument:UserDocument){
      return this.http.put(`${this.url}/update`, userDocument,{headers:{"Authorization":SessionVariables.connectionToken}});
  }
  
  delete(id:number){
      return this.http.delete(`${this.url}/delete/${id}`,{headers:{"Authorization":SessionVariables.connectionToken}});
  }

}
