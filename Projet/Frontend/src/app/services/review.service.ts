import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionVariables } from '../common/SessionVariables';
import { Review } from '../models/Review';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  constructor(private http: HttpClient) { }

  url=SessionVariables.serverUrl+'/review';

  getAll(){
    SessionVariables.getSessionVariables();
      return this.http.get<Review[]>(`${this.url}`,{headers:{"Authorization":SessionVariables.connectionToken}});
  }
  
  getById(id:number){
    SessionVariables.getSessionVariables();
      return this.http.get<Review>(`${this.url}/${id}`,{headers:{"Authorization":SessionVariables.connectionToken}});
  }

  
  add(review:Review){
    SessionVariables.getSessionVariables();
    console.log(SessionVariables.connectionToken)
      return this.http.post(`${this.url}/save`, review,{headers:{"Authorization":SessionVariables.connectionToken}});
  }

  update(review:Review){
    SessionVariables.getSessionVariables();
      return this.http.put(`${this.url}/update`, review,{headers:{"Authorization":SessionVariables.connectionToken}});
  }
  
  delete(id:number){
    
    SessionVariables.getSessionVariables();
      return this.http.delete(`${this.url}/delete/${id}`,{headers:{"Authorization":SessionVariables.connectionToken}});
  }
}
