import { Injectable } from '@angular/core';
import {DataPoint} from "./data-point";
import {Observable} from "rxjs";
import {ApiService} from "../api/api.service";

@Injectable({
  providedIn: 'root'
})
export class TimeseriesService {

  constructor(private apiService: ApiService) {
  }

  public getTimeseries(): Observable<DataPoint[]> {
    return this.apiService.get('timeseries');
  }
}
