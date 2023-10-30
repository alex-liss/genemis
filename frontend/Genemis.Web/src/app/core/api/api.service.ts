import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private readonly backend: string;

  private options = {headers: new HttpHeaders().set('Content-Type', 'application/json')};

  constructor( private http: HttpClient) {
    this.backend = "http://localhost:8080/";
  }

  public get(path: string): Observable<any> {
    return this.http.get(this.backend + path, this.options);
  }

}
