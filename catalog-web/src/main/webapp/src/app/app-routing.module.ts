import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RecordsComponent} from "./records/records.component";
import {UsersComponent} from "./users/users.component";
import {TransactionsComponent} from "./transactions/transactions.component";
import {RecordsAddComponent} from "./records/records-add/records-add.component";


const routes: Routes = [
  {path: 'records', component: RecordsComponent},
  {path: 'users', component: UsersComponent},
  {path: 'transactions', component: TransactionsComponent},
  {path: 'records-add', component: RecordsAddComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
