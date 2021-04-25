import { Action } from "@ngrx/store";

export function simpleReducer(state: string = "Welcome to the OnlineMusicStore!", action: Action) {
  console.log(action.type, state);

  switch (action.type) {
    case 'ENGLISH':
      return state = "Welcome to the OnlineMusicStore!"
    case 'ROMANIAN':
      return state = 'Bine ati venit la OnlineMusicStore';
    default:
      return state;
  }
}
