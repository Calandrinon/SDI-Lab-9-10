import {Component, OnInit} from '@angular/core';
import {Record, RecordType} from "../shared/record.model";
import {RecordService} from "../shared/record.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import { Store } from "@ngrx/store";
import { AppState } from "../../app.state";
import * as OnlineMusicStoreActions from "../../actions/onlinemusicstore.actions";
import { Observable } from "rxjs";

@Component({
  selector: 'app-records-add',
  templateUrl: './records-add.component.html',
  styleUrls: ['./records-add.component.css']
})
export class RecordsAddComponent implements OnInit {
  formGroup: FormGroup;

  constructor(private recordService: RecordService,
              private formBuilder: FormBuilder,
              private store: Store<AppState>) { }

  ngOnInit(): void {
    this.formGroup = this.formBuilder.group({
      "recordId": ["", Validators.required],
      "albumName": ["", Validators.required],
      "price": ["", Validators.required],
      "inStock": ["", Validators.required],
      "typeOfRecord": ["", Validators.required]
    });

    this.formGroup.valueChanges.subscribe(console.log);
  }

  saveRecord(): void {
    let recordType: RecordType;

    switch(this.formGroup.value.typeOfRecord.toUpperCase()) {
      case 'CD': recordType = RecordType.CD; break;
      case 'VINYL': recordType = RecordType.VINYL; break;
      case 'TAPE': recordType = RecordType.TAPE; break;
    }

    const record: Record = <Record>{
      id: +this.formGroup.value.recordId,
      albumName: this.formGroup.value.albumName,
      price: +this.formGroup.value.price,
      inStock: +this.formGroup.value.inStock,
      typeOfRecord: recordType};
    console.log("The record instance: ", record);
    this.recordService.saveRecord(record)
      .subscribe(record => console.log("The added record: ", record));
    this.store.dispatch(new OnlineMusicStoreActions.AddRecord(record));
  }
}
