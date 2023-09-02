import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Greeting} from "./greeting";

@Injectable({
  providedIn: 'root'
})
export class GreetingService {

  private backend: string;

  private options = {headers: new HttpHeaders().set('Content-Type', 'application/json')};

  constructor(private http: HttpClient) {
    this.backend = "http://localhost:8080/greeting";
  }

  public getGreeting(): Observable<Greeting> {
    return this.http.get<Greeting>(this.backend, this.options);
  }
}
