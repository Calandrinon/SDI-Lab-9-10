import { Component, OnInit } from '@angular/core';
import {TransactionService} from "../shared/transaction.service";
import {Transaction} from "../shared/transaction.model";

@Component({
  selector: 'app-transactions-list',
  templateUrl: './transactions-list.component.html',
  styleUrls: ['./transactions-list.component.css']
})
export class TransactionsListComponent implements OnInit {
  transactions: Transaction[];

  constructor(private transactionService: TransactionService) { }

  ngOnInit(): void {
    this.transactionService.getTransactions().subscribe(transactions => {
      console.log(transactions['transactions']);
      this.transactions = transactions['transactions'];
    });
  }

}
