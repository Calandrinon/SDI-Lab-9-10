import {Component, OnInit} from '@angular/core';
import {RecordService} from "../shared/record.service";
import {Record, RecordType} from "../shared/record.model";

@Component({
  selector: 'app-records-remove',
  templateUrl: './records-remove.component.html',
  styleUrls: ['./records-remove.component.css']
})
export class RecordsRemoveComponent implements OnInit {

  constructor(private recordService: RecordService) { }

  ngOnInit(): void {
  }

  removeRecord(id: string): void {
    const record: Record = <Record>{id: +id, price: 0, inStock: 0, typeOfRecord: RecordType.CD};
    console.log("The record instance: ", record);
    this.recordService.removeRecord(record)
      .subscribe(record => console.log("The removed record: ", record));
  }
}
