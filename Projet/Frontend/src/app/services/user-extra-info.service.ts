import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionVariables } from '../common/SessionVariables';
import { UserExtraInfo } from '../models/UserExtraInfo';

@Injectable({
  providedIn: 'root'
})
export class UserExtraInfoService {

  constructor(private http: HttpClient) { }

    url=SessionVariables.serverUrl+'/userExtraInfo';

    getAll(){
        return this.http.get<UserExtraInfo[]>(`${this.url}`,{headers:{"Authorization":SessionVariables.connectionToken}});
    }
    
    getById(id:number){
        return this.http.get<UserExtraInfo>(`${this.url}/${id}`,{headers:{"Authorization":SessionVariables.connectionToken}});
    }

    
    add(userExtraInfo:UserExtraInfo){
        return this.http.post(`${this.url}/save`, userExtraInfo);
    }

    update(userExtraInfo:UserExtraInfo){
        return this.http.put(`${this.url}/update`, userExtraInfo,{headers:{"Authorization":SessionVariables.connectionToken}});
    }
    
    delete(id:number){
        return this.http.delete(`${this.url}/delete/${id}`,{headers:{"Authorization":SessionVariables.connectionToken}});
    }
    
    Specialites(){
        return this.http.get(`${this.url}/specialites`);
    }
}
