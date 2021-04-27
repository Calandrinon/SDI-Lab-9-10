import { Component, OnInit } from '@angular/core';
import {AppState} from "../../app.state";
import {Store} from "@ngrx/store";
import { Record } from "../shared/record.model";
import {FilterRecordsByStock} from "../../actions/onlinemusicstore.actions";

@Component({
  selector: 'app-records-filter',
  templateUrl: './records-filter.component.html',
  styleUrls: ['./records-filter.component.css']
})
export class RecordsFilterComponent implements OnInit {
  records: Record[] = [];
  filterButtonPressed: boolean;

  constructor(private store: Store<AppState>) { }

  ngOnInit(): void {
    this.filterButtonPressed = false;
  }

  filterByInStockGreaterThan(minimumInStock: string): void {
    this.store.dispatch(new FilterRecordsByStock(+minimumInStock));
    this.store.select("records").subscribe(records => {
      console.log("[FROM THE COMPONENT] The records have been selected from the store: ");
      console.log(records);
      this.records = records['records'];
      this.filterButtonPressed = true;
    });
  }
}
