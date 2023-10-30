import { Injectable } from '@angular/core';
import {DataPoint} from "./data-point";
import {Observable} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class TimeseriesService {

  private backend: string;

  private options = {headers: new HttpHeaders().set('Content-Type', 'application/json')};

  constructor(private http: HttpClient) {
    this.backend = "http://localhost:8080/timeseriesdata";
  }

  public getTimeseries(): Observable<DataPoint[]> {
    return this.http.get<DataPoint[]>(this.backend, this.options);
  }
}
