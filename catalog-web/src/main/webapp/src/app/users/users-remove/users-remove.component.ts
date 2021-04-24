import { Component, OnInit } from '@angular/core';
import {UserService} from "../shared/users.service";
import {User} from "../shared/users.model";

@Component({
  selector: 'app-users-remove',
  templateUrl: './users-remove.component.html',
  styleUrls: ['./users-remove.component.css']
})
export class UsersRemoveComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  removeUser(id: string): void {
    const user: User = <User>{id: +id};
    this.userService.removeUser(user)
      .subscribe(user => console.log("The removed user: ", user));
  }
}
