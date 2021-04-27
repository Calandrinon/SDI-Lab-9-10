import { Record } from "./records/shared/record.model";

export interface AppState {
  readonly records: Record[];
  readonly message: string;
  areTheRecordsLoaded: boolean;
  loading: boolean;
}
