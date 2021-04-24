import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Transaction} from "./transaction.model";
import {User} from "../../users/shared/users.model";
import {Record} from "../../records/shared/record.model";


@Injectable()
export class TransactionService{
  private transactionsUrl = 'http://localhost:8080/api/transactions';
  private singleTransactionUrl = 'http://localhost:8080/api/transaction';

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

  saveTransaction(transaction: Transaction): Observable<Transaction>{
    return this.httpClient.post<Transaction>(this.singleTransactionUrl, transaction);
  }
}
