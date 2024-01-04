import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Greeting} from "./greeting";
import {ApiService} from "../api/api.service";

@Injectable({
  providedIn: 'root'
})
export class GreetingService {

  constructor(private apiService: ApiService) {
  }

  public getGreeting(): Observable<Greeting> {
    return this.apiService.get('greeting');
  }
}
