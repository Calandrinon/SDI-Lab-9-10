import { Injectable } from "@angular/core";
import { Action } from "@ngrx/store";
import { Record } from "../records/shared/record.model";
import { User } from "../users/shared/users.model";
import { Transaction } from "../transactions/shared/transaction.model";
import { Artist } from "../artists/shared/artist.model";

export const LOAD_RECORDS = "[RECORD] Load";
export const LOAD_RECORDS_SUCCESS = "[RECORD] Load: success";
export const LOAD_RECORDS_FAILURE = "[RECORD] Load: failed";
export const ADD_RECORD = "[RECORD] Add";
export const UPDATE_RECORD = "[RECORD] Update";
export const REMOVE_RECORD = "[RECORD] Remove";
export const FILTER_RECORD = "[RECORD] Filter";
export const FILTER_RECORDS_SUCCESS = "[RECORD] Filter: success";
export const FILTER_RECORDS_FAILURE = "[RECORD] Filter: failure";
export const SORT_RECORD = "[RECORD] Sort";
export const SORT_RECORDS_SUCCESS = "[RECORD] Sort: success";
export const SORT_RECORDS_FAILURE = "[RECORD] Sort: failure";


export class LoadRecords implements Action {
  readonly type = LOAD_RECORDS;

  constructor() {}
}

export class LoadRecordsSuccess implements Action {
  readonly type = LOAD_RECORDS_SUCCESS;

  constructor(public payload: Record[]) {
  }
}

export class LoadRecordsFailure implements Action {
  readonly type = LOAD_RECORDS_FAILURE;

  constructor(public payload: Record[]) {
  }
}

export class AddRecord implements Action {
  readonly type = ADD_RECORD;

  constructor(public payload: Record) {
  }
}

export class RemoveRecord implements Action {
  readonly type = REMOVE_RECORD;

  constructor(public payload: number) {
  }
}

export class UpdateRecord implements Action {
  readonly type = UPDATE_RECORD;

  constructor(public payload: Record) {
  }
}

export class FilterRecordsByStock implements Action {
  readonly type = FILTER_RECORD;

  constructor(public payload: number) {
  }
}

export class FilterRecordsByStockSuccess implements Action {
  readonly type = FILTER_RECORDS_SUCCESS;

  constructor(public payload: Record[]) {
  }
}

export class FilterRecordsByStockFailure implements Action {
  readonly type = FILTER_RECORDS_FAILURE;

  constructor(public payload: Record[]) {
  }
}

export class SortRecordsByPrice implements Action {
  readonly type = SORT_RECORD;

  constructor() {
  }
}

export class SortRecordsByPriceSuccess implements Action {
  readonly type = SORT_RECORDS_SUCCESS;

  constructor(public payload: Record[]) {
  }
}

export class SortRecordsByPriceFailure implements Action {
  readonly type = SORT_RECORDS_FAILURE;

  constructor(public payload: Record[]) {
  }
}



export type Actions = LoadRecords
  | LoadRecordsSuccess | LoadRecordsFailure
  | AddRecord | UpdateRecord | RemoveRecord
  | FilterRecordsByStock | FilterRecordsByStockSuccess | FilterRecordsByStockFailure
  | SortRecordsByPrice | SortRecordsByPriceSuccess | SortRecordsByPriceFailure;

