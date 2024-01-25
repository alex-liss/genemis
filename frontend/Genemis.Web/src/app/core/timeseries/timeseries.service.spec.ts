import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TimeseriesService } from './timeseries.service';
import {of} from "rxjs";
import {ApiService} from "../api/api.service";

describe('TimeseriesService', () => {
  let service: TimeseriesService;
  let mockApiService: any;

  const timeseries = [
    {
      "value": 0.0,
      "utcDateTime": "2020-01-01T00:00:00"
    },
    {
      "value": 1.0,
      "utcDateTime": "2020-01-01T00:01:00"
    },
  ];

  beforeEach(() => {
    mockApiService = jasmine.createSpyObj(['get']);

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers:[
        TimeseriesService,
        {provide: ApiService, useValue: mockApiService}
      ]
    });

    service = TestBed.inject(TimeseriesService);
  });

  it('should return array of data points', (done: DoneFn) => {
    mockApiService.get.and.returnValue(of(timeseries));
    service.getTimeseries().subscribe(timeseries => {
      expect(timeseries).toBeTruthy();
      expect(timeseries.length).toEqual(2);
      done();
    });
  });

});
