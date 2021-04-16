import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {Record} from "./record.model";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";


@Injectable()
export class RecordService {
  private recordsUrl = 'http://localhost:8080/api/records';
  private addUrl = 'http://localhost:8080/api/record';

  constructor(private httpClient: HttpClient) {
  }

  getRecords(): Observable<Record[]> {
    return this.httpClient
      .get<Array<Record>>(this.recordsUrl);
  }

  getRecord(id: number): Observable<Record> {
    return this.getRecords()
      .pipe(
        map(records => records.find(record => record.id === id))
      );
  }

  saveRecord(record: Record): Observable<Record> {
    return this.httpClient.post<Record>(this.addUrl, record);
  }

  update(record: Record): Observable<Record> {
    const url = `${this.recordsUrl}/${record.id}`;
    return this.httpClient
      .put<Record>(url, record);
  }

}
