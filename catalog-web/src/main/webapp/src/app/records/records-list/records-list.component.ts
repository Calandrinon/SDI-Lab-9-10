import { Component, OnInit } from '@angular/core';
import { Record } from '../shared/record.model';
import {RecordService} from "../shared/record.service";

@Component({
  selector: 'app-records-list',
  templateUrl: './records-list.component.html',
  styleUrls: ['./records-list.component.css']
})
export class RecordsListComponent implements OnInit {
  records: Record[] = [];

  constructor(private recordService: RecordService) { }

  ngOnInit(): void {
    this.recordService.getRecords().subscribe(records => {
      console.log(records['records']);
      this.records = records['records'];
    });

  }

}
