import { Component, OnInit } from '@angular/core';
import {UserService} from "../shared/users.service";
import {User} from "../shared/users.model";

@Component({
  selector: 'app-users-add',
  templateUrl: './users-add.component.html',
  styleUrls: ['./users-add.component.css']
})
export class UsersAddComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  saveUser(id: string, firstName: string, lastName: string): void {
    const user: User = <User>{id: +id, firstName, lastName, numberOfTransactions: 0};
    this.userService.saveUser(user)
      .subscribe(user => console.log("The added user: ", user));
  }
}
