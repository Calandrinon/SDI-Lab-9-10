import { Component, OnInit } from '@angular/core';
import {UserService} from "../shared/users.service";
import {User} from "../shared/users.model";

@Component({
  selector: 'app-users-update',
  templateUrl: './users-update.component.html',
  styleUrls: ['./users-update.component.css']
})
export class UsersUpdateComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  updateUser(id: string, firstName: string, lastName: string): void {
    const user: User = <User>{id: +id, firstName, lastName};
    this.userService.updateUser(user)
      .subscribe(user => console.log("The updated user: ", user));
  }
}
