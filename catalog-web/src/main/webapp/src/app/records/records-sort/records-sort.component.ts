import { Component, OnInit } from '@angular/core';
import {AppState} from "../../app.state";
import {Store} from "@ngrx/store";
import { Record } from "../shared/record.model";
import {FilterRecordsByStock, SortRecordsByPrice} from "../../actions/onlinemusicstore.actions";

@Component({
  selector: 'app-records-sort',
  templateUrl: './records-sort.component.html',
  styleUrls: ['./records-sort.component.css']
})
export class RecordsSortComponent implements OnInit {
  records: Record[] = [];

  constructor(private store: Store<AppState>) { }

  ngOnInit(): void {
    this.store.dispatch(new SortRecordsByPrice());
    this.store.select("records").subscribe(records => {
      console.log("[FROM THE RECORDS.SORT COMPONENT] The records have been selected from the store: ");
      console.log(records['records']);
      this.records = records['records'];
    });
  }
}
