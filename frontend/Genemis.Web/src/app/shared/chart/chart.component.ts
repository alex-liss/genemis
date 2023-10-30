import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {TimeseriesService} from "../../core/timeseries/timeseries.service";
import {ChartData, ChartOptions, ChartType} from "chart.js";
import {DataPoint} from "../../core/timeseries/data-point";

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit, OnDestroy {

  type: ChartType = 'line';


  data: ChartData = {
    labels: [],
    datasets: []
  };

  options: ChartOptions = {
    maintainAspectRatio: false,
  };

  subscription!: Subscription;

  constructor(private timeSeriesService: TimeseriesService) {
  }

  ngOnInit(): void {

    this.subscription = this.timeSeriesService.getTimeseries().subscribe((dataPoints: DataPoint[]) => {
      this.data = {
        labels: dataPoints.map(val => val.utcDateTime),
        datasets: [{label: "Price", data: dataPoints.map(val => val.value)}]
      }
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
