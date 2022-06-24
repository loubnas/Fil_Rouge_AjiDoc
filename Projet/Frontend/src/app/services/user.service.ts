import {HttpClient} from '@angular/common/http'
import { Injectable } from '@angular/core';
import { SessionVariables } from '../common/SessionVariables';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {

    constructor(private http: HttpClient) { }

    url=SessionVariables.serverUrl+'/user';
    loginUrl=SessionVariables.serverUrl+'/token/login';

    getAll(){
        return this.http.get<User[]>(`${this.url}`,{headers:{"Authorization":SessionVariables.connectionToken}});
    }
    
    getById(id:number){
        return this.http.get<User>(`${this.url}/${id}`,{headers:{"Authorization":SessionVariables.connectionToken}});
    }

    login(email:string,password:string){
        return this.http.post(`${this.loginUrl}`, {"email":email,"password":password},{observe:"response"});
    }

    add(user:User){
        return this.http.post<User>(`${this.url}/save`, user);
    }

    update(user:User){
        return this.http.put<User>(`${this.url}/update`, user,{headers:{"Authorization":SessionVariables.connectionToken}});
    }
    
    delete(id:number){
        return this.http.delete(`${this.url}/delete/${id}`,{headers:{"Authorization":SessionVariables.connectionToken}});
    }
    
    Villes(){
        return this.http.get(`${this.url}/villes`);
    }

}
