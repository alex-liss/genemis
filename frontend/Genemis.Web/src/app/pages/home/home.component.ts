import { Component, OnInit } from '@angular/core';
import {Greeting} from "../../core/greeting/greeting";
import {Subscription} from "rxjs";
import {GreetingService} from "../../core/greeting/greeting.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  greeting: Greeting = new Greeting;
  subscription!: Subscription;

  constructor(private greetingService: GreetingService) {
  }

  ngOnInit(): void {
    this.subscription = this.greetingService.getGreeting().subscribe(value => {
      this.greeting = value;
    });
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
