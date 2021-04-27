import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import {from, interval, Observable} from 'rxjs';
import { AppState } from "./app.state";
import {exhaustMap, flatMap, map, mergeMap, take} from "rxjs/operators";
import {LoadRecords} from "./actions/onlinemusicstore.actions";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Online Music Store';
  //message: Observable<string>;

  constructor(private store: Store<AppState>) {
  }

  ngOnInit() {
    console.log("AppComponent's ngOnInit is running...");
    //this.message = this.store.select("message");
    this.store.dispatch(new LoadRecords());
  }

  /**
  englishMessage() {
    this.store.dispatch({type: 'ENGLISH'});
  }

  romanianMessage() {
    this.store.dispatch({type: 'ROMANIAN'});
  }
  **/
}
