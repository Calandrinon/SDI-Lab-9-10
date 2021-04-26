import {Record, RecordType} from "../records/shared/record.model";
import { Action } from "@ngrx/store";
import * as OnlineMusicStoreActions from '../actions/onlinemusicstore.actions';

const initialState: Record = {
  id: -1,
  price: -1,
  albumName: "No album fetched from the server yet...",
  inStock: -1,
  typeOfRecord: RecordType.CD
}

export function recordReducer(state: Record[] = [initialState], action: OnlineMusicStoreActions.Actions) {
  switch (action.type) {
    case OnlineMusicStoreActions.ADD_RECORD:
      return [...state, action.payload];
    default:
      return state;
  }
}
