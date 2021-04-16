import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {User} from "./users.model";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";


@Injectable()
export class UserService {
  private usersUrl = 'http://localhost:8080/api/users';

  constructor(private httpClient: HttpClient) {
  }

  getUsers(): Observable<User[]> {
    return this.httpClient
      .get<Array<User>>(this.usersUrl);
  }

  getUser(id: number): Observable<User> {
    return this.getUsers()
      .pipe(
        map(users => users.find(users => users.id === id))
      );
  }

  update(user): Observable<User> {
    const url = `${this.usersUrl}/${user.id}`;
    return this.httpClient
      .put<User>(url, user);
  }

}
