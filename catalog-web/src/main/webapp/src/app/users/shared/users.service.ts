import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {User} from "./users.model";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Record} from "../../records/shared/record.model";


@Injectable()
export class UserService {
  private usersUrl = 'http://localhost:8080/api/users';
  private singleUserUrl = 'http://localhost:8080/api/user';

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

  saveUser(user: User): Observable<User> {
    return this.httpClient.post<User>(this.singleUserUrl, user);
  }

  updateUser(user): Observable<User> {
    const url = `${this.singleUserUrl}/${user.id}`;
    return this.httpClient
      .put<User>(url, user);
  }

  removeUser(user): Observable<User> {
    const url = `${this.singleUserUrl}/${user.id}`;
    return this.httpClient
      .delete<User>(url);
  }
}
