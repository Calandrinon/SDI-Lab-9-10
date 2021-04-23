import {Component, OnInit} from '@angular/core';
import {Record, RecordType} from "../shared/record.model";
import {RecordService} from "../shared/record.service";

@Component({
  selector: 'app-records-add',
  templateUrl: './records-add.component.html',
  styleUrls: ['./records-add.component.css']
})
export class RecordsAddComponent implements OnInit {

  constructor(private recordService: RecordService) { }

  ngOnInit(): void {
  }

  saveRecord(id: string, albumName: string, price: string, stock: string, type: string): void {
    let recordType: RecordType;

    switch(type.toUpperCase()) {
      case 'CD': recordType = RecordType.CD; break;
      case 'VINYL': recordType = RecordType.VINYL; break;
      case 'TAPE': recordType = RecordType.TAPE; break;
    }

    const record: Record = <Record>{id: +id, albumName, price: +price, inStock: +stock, typeOfRecord: recordType};
    console.log("The record instance: ", record);
    this.recordService.saveRecord(record)
      .subscribe(record => console.log("The added record: ", record));
  }
}
