import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { AppState } from "./app.state";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Online Music Store';
  message: Observable<string>;

  constructor(private store: Store<AppState>) {
  }

  ngOnInit() {
    this.message = this.store.select("message");
  }

  englishMessage() {
    this.store.dispatch({type: 'ENGLISH'});
  }

  romanianMessage() {
    this.store.dispatch({type: 'ROMANIAN'});
  }
}
