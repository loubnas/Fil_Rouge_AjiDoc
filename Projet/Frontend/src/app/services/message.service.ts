import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionVariables } from '../common/SessionVariables';
import { Message } from '../models/Message';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private http: HttpClient) { }

  url=SessionVariables.serverUrl+'/message';

  getAll(){
      return this.http.get<Message[]>(`${this.url}`);
  }
 
  add(message:Message){
      return this.http.post<Message>(`${this.url}/save`, message);
  }

}
