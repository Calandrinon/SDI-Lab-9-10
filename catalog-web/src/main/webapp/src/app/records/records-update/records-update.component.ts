import { Component, OnInit } from '@angular/core';
import {RecordService} from "../shared/record.service";
import {Record, RecordType} from "../shared/record.model";

@Component({
  selector: 'app-records-update',
  templateUrl: './records-update.component.html',
  styleUrls: ['./records-update.component.css']
})
export class RecordsUpdateComponent implements OnInit {

  constructor(private recordService: RecordService) { }

  ngOnInit(): void {
  }

  updateRecord(id: string, albumName: string, price: string, stock: string, type: string): void {
    let recordType: RecordType;

    switch(type.toUpperCase()) {
      case 'CD': recordType = RecordType.CD; break;
      case 'VINYL': recordType = RecordType.VINYL; break;
      case 'TAPE': recordType = RecordType.TAPE; break;
    }

    const record: Record = <Record>{id: +id, albumName, price: +price, inStock: +stock, typeOfRecord: recordType};
    console.log("The record instance: ", record);
    this.recordService.updateRecord(record)
      .subscribe(record => console.log("The updated record: ", record));
  }

}
