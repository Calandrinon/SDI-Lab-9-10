import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Transaction} from "./transaction.model";


@Injectable()
export class TransactionService{
  private transactionsUrl = 'http://localhost:8080/api/transactions';

  constructor(private httpClient: HttpClient) {
  }

  getTransactions(): Observable<Transaction[]> {
    return this.httpClient
      .get<Array<Transaction>>(this.transactionsUrl);
  }

  getTransaction(id: number): Observable<Transaction> {
    return this.getTransactions()
      .pipe(
        map(transactions => transactions.find(transaction => transaction.id === id))
      );
  }

  update(transaction): Observable<Transaction> {
    const url = `${this.transactionsUrl}/${transaction.id}`;
    return this.httpClient
      .put<Transaction>(url, transaction);
  }

}
