import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import {EMPTY, of} from 'rxjs';
import {map, mergeMap, catchError, switchMap} from 'rxjs/operators';
import { RecordService } from '../records/shared/record.service';
import * as OnlineMusicStoreActions from '../actions/onlinemusicstore.actions';
import {Record, RecordType} from "../records/shared/record.model";
import {FilterRecordsByStock, SortRecordsByPrice} from "../actions/onlinemusicstore.actions";

@Injectable()
export class RecordEffects {

  loadRecords$ = createEffect(() => this.actions$.pipe(
    ofType("[RECORD] Load"),
    switchMap(() => this.recordService.getRecords().pipe(
      map(records => new OnlineMusicStoreActions.LoadRecordsSuccess(records)),
      catchError(() => of(new OnlineMusicStoreActions.LoadRecordsFailure([new Record(-1,-1,"error",-1,RecordType.CD)])))
      ))
  ));

  filterRecords$ = createEffect(() => this.actions$.pipe(
    ofType("[RECORD] Filter"),
    switchMap((action: FilterRecordsByStock) => this.recordService.filterRecordsWithInStockGreaterThan(action.payload).pipe(
      map(records => new OnlineMusicStoreActions.FilterRecordsByStockSuccess(records)),
      catchError(() => of(new OnlineMusicStoreActions.FilterRecordsByStockFailure([new Record(-1,-1,"error",-1,RecordType.CD)])))
    ))
  ));

  sortRecords$ = createEffect(() => this.actions$.pipe(
    ofType("[RECORD] Sort"),
    switchMap((action: SortRecordsByPrice) => this.recordService.sortRecordsByPrice().pipe(
      map(records => new OnlineMusicStoreActions.SortRecordsByPriceSuccess(records)),
      catchError(() => of(new OnlineMusicStoreActions.SortRecordsByPriceFailure([new Record(-1,-1,"error",-1,RecordType.CD)])))
    ))
  ));

  constructor(private actions$: Actions,
              private recordService: RecordService) {
    this.recordService.sortRecordsByPrice().subscribe(records => {
      console.log("From RecordEffects constructor:");
      console.log(records);
    })
  }

}
