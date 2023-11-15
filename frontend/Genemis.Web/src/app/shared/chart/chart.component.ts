import {Component, OnDestroy, OnInit, } from '@angular/core';
import {Subscription} from "rxjs";
import {TimeseriesService} from "../../core/timeseries/timeseries.service";
import {ChartData, ChartOptions, ChartType} from "chart.js";
import {DataPoint} from "../../core/timeseries/data-point";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit, OnDestroy {

  type: ChartType = 'line';
  backgroundColor: string = '#3E4040';
  borderColor: string = '#637371';

  data: ChartData = {
    labels: [],
    datasets: []
  };


  options: ChartOptions = {
    maintainAspectRatio: false,
    elements: {
      line: {
        tension: 0.5,
      },
    },
  };

  subscription!: Subscription;

  constructor(private timeSeriesService: TimeseriesService,
              private datePipe: DatePipe) {
  }

  ngOnInit(): void {

    this.subscription = this.timeSeriesService.getTimeseries().subscribe((dataPoints: DataPoint[]) => {
      this.data = {
        labels: dataPoints.map(val => this.datePipe.transform(val.utcDateTime, 'yyyy-MM-dd')),
        datasets: [{
          label: "Price",
          data: dataPoints.map(val => val.value),
          backgroundColor: this.backgroundColor,
          borderColor: this.borderColor,
          pointBackgroundColor: this.backgroundColor,
        }]
      }
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
