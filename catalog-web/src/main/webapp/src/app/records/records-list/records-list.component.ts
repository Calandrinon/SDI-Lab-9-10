import { Component, OnInit } from '@angular/core';
import { Record } from '../shared/record.model';
import { RecordService } from "../shared/record.service";
import { Observable } from "rxjs";
import { Store } from "@ngrx/store";
import { AppState } from "../../app.state";


@Component({
  selector: 'app-records-list',
  templateUrl: './records-list.component.html',
  styleUrls: ['./records-list.component.css']
})
export class RecordsListComponent implements OnInit {
  records: Record[];

  constructor(private recordService: RecordService,
              private store: Store<AppState>) {
    this.recordService.getRecords().subscribe(records => {
      console.log("From the constructor of RecordsListComponent:");
      console.log(records['records']);
      this.records = records['records'];
    });
  }

  ngOnInit(): void {
    console.log("selecting the data from the store");
    this.store.select('records').subscribe(records => {
      console.log("From the ngOnInit of RecordsListComponent:");
      console.log(records);
    });
  }

}
