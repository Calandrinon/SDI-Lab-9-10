import { Component, OnInit } from '@angular/core';
import { Record } from "./shared/record.model";

@Component({
  selector: 'app-records',
  templateUrl: './records.component.html',
  styleUrls: ['./records.component.css']
})
export class RecordsComponent implements OnInit {
  record = Record;

  constructor() { }

  ngOnInit(): void {
  }

}
