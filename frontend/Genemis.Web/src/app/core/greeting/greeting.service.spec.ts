import { TestBed } from '@angular/core/testing';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import { GreetingService } from './greeting.service';
import {ApiService} from "../api/api.service";
import {of} from "rxjs";

describe('GreetingService', () => {
  let service: GreetingService;
  let mockApiService: any;

  beforeEach(() => {
    mockApiService = jasmine.createSpyObj(['get']);

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        GreetingService,
        {provide: ApiService, useValue: mockApiService}
      ]
    });

    service = TestBed.inject(GreetingService);
  });

  it('should return string greeting', (done: DoneFn) => {
    mockApiService.get.and.returnValue(of({message: 'Hello, World!'}));
    service.getGreeting().subscribe(greeting => {
      expect(greeting).toBeTruthy();
      expect(greeting.message).toBe('Hello, World!');
      done();
    });
  });

});
