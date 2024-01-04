import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TimeseriesService } from './timeseries.service';

describe('TimeseriesService', () => {
  let service: TimeseriesService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [TimeseriesService]
    });
    service = TestBed.inject(TimeseriesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
