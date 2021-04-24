import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import { RecordsComponent } from './records/records.component';
import { UsersComponent } from './users/users.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { RecordsListComponent } from './records/records-list/records-list.component';
import { TransactionsListComponent } from './transactions/transactions-list/transactions-list.component';
import { UsersListComponent } from './users/users-list/users-list.component';
import {RecordService} from "./records/shared/record.service";
import {UserService} from "./users/shared/users.service";
import {TransactionService} from "./transactions/shared/transaction.service";
import { RecordsAddComponent } from './records/records-add/records-add.component';
import { RecordsUpdateComponent } from './records/records-update/records-update.component';
import { RecordsRemoveComponent } from './records/records-remove/records-remove.component';
import { UsersAddComponent } from './users/users-add/users-add.component';
import { UsersRemoveComponent } from './users/users-remove/users-remove.component';
import { UsersUpdateComponent } from './users/users-update/users-update.component';
import { TransactionsAddComponent } from './transactions/transactions-add/transactions-add.component';


@NgModule({
  declarations: [
    AppComponent,
    RecordsComponent,
    UsersComponent,
    TransactionsComponent,
    RecordsListComponent,
    TransactionsListComponent,
    UsersListComponent,
    RecordsAddComponent,
    RecordsUpdateComponent,
    RecordsRemoveComponent,
    UsersAddComponent,
    UsersRemoveComponent,
    UsersUpdateComponent,
    TransactionsAddComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [RecordService, UserService, TransactionService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
