import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RecordsComponent} from "./records/records.component";
import {UsersComponent} from "./users/users.component";
import {TransactionsComponent} from "./transactions/transactions.component";
import {RecordsAddComponent} from "./records/records-add/records-add.component";
import {RecordsUpdateComponent} from "./records/records-update/records-update.component";
import {RecordsRemoveComponent} from "./records/records-remove/records-remove.component";
import {UsersAddComponent} from "./users/users-add/users-add.component";
import {UsersUpdateComponent} from "./users/users-update/users-update.component";
import {UsersRemoveComponent} from "./users/users-remove/users-remove.component";
import {ArtistsAddComponent} from "./artists/artists-add/artists-add.component";
import {ArtistsComponent} from "./artists/artists.component";
import {ArtistsUpdateComponent} from "./artists/artists-update/artists-update.component";
import {ArtistsRemoveComponent} from "./artists/artists-remove/artists-remove.component";


const routes: Routes = [
  {path: 'records', component: RecordsComponent},
  {path: 'users', component: UsersComponent},
  {path: 'transactions', component: TransactionsComponent},
  {path: 'artists', component: ArtistsComponent},
  {path: 'records-add', component: RecordsAddComponent},
  {path: 'records-update', component: RecordsUpdateComponent},
  {path: 'records-remove', component: RecordsRemoveComponent},
  {path: 'users-add', component: UsersAddComponent},
  {path: 'users-update', component: UsersUpdateComponent},
  {path: 'users-remove', component: UsersRemoveComponent},
  {path: 'artists-add', component: ArtistsAddComponent},
  {path: 'artists-update', component: ArtistsUpdateComponent},
  {path: 'artists-remove', component: ArtistsRemoveComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
