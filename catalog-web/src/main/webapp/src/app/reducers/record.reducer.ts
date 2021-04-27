import {Record, RecordType} from "../records/shared/record.model";
import { Action } from "@ngrx/store";
import * as OnlineMusicStoreActions from '../actions/onlinemusicstore.actions';
import { AppState } from "../app.state";
import {act, Actions} from "@ngrx/effects";

const initialState: AppState = <AppState> {
  records: [],
  areTheRecordsLoaded: false,
  loading: false,
  message: "Welcome to the OnlineMusicStore!"
}

export function recordReducer(state: AppState = initialState, action: OnlineMusicStoreActions.Actions) {
  switch (action.type) {
    case OnlineMusicStoreActions.LOAD_RECORDS:
      return {
        ...state,
        loading: true
      };

    case OnlineMusicStoreActions.LOAD_RECORDS_SUCCESS:
      console.log("Handling the LOAD_RECORDS_SUCCESS action...");
      console.log("These are the records obtained from the effect/service request:");
      console.log(action.payload['records']);
      return {
        ...state,
        areTheRecordsLoaded: true,
        loading: false,
        records: action.payload['records']
      };

    case OnlineMusicStoreActions.LOAD_RECORDS_FAILURE:
      return {
        ...state,
        areTheRecordsLoaded: false,
        loading: false
      };

    case OnlineMusicStoreActions.ADD_RECORD:
      return {
        ...state,
        records: [...state.records, action.payload]
      };

    case OnlineMusicStoreActions.FILTER_RECORD:
      return {
        ...state,
        loading: false,
      };

    case OnlineMusicStoreActions.FILTER_RECORDS_SUCCESS:
      console.log("[FROM THE REDUCER] Filter records (success):");
      console.log(action.payload['records']);
      return {
        ...state,
        areTheRecordsLoaded: true,
        loading: false,
        records: action.payload['records']
      };

    case OnlineMusicStoreActions.FILTER_RECORDS_FAILURE:
      return {
        ...state,
        areTheRecordsLoaded: false,
        loading: false
      };

    case OnlineMusicStoreActions.SORT_RECORD:
      return {
        ...state,
        loading: true,
      };

    case OnlineMusicStoreActions.SORT_RECORDS_SUCCESS:
      console.log("[FROM THE REDUCER] Sort records (success):");
      console.log(action.payload['records']);
      return {
        ...state,
        areTheRecordsLoaded: true,
        loading: false,
        records: action.payload['records']
      };

    case OnlineMusicStoreActions.SORT_RECORDS_FAILURE:
      return {
        ...state,
        areTheRecordsLoaded: false,
        loading: false
      };

    default:
      return state;
  }
}
