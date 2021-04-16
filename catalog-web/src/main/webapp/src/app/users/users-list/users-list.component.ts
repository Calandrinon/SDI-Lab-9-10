import { Component, OnInit } from '@angular/core';
import {User} from "../shared/users.model";
import {UserService} from "../shared/users.service";

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {
  users: User[];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getUsers().subscribe(users => {
      console.log(users['users']);
      this.users = users['users'];
    });
  }

}
