import { Component, OnInit } from '@angular/core';
import {User} from "../../users/shared/users.model";
import {Transaction} from "../shared/transaction.model";
import {TransactionService} from "../shared/transaction.service";

@Component({
  selector: 'app-transactions-add',
  templateUrl: './transactions-add.component.html',
  styleUrls: ['./transactions-add.component.css']
})
export class TransactionsAddComponent implements OnInit {

  constructor(private transactionService: TransactionService) { }

  ngOnInit(): void {
  }

  saveTransaction(userId: string, recordId: string, quantity: string): void {
    const transaction: Transaction = <Transaction>{id: 0, userID: +userId, recordID: +recordId, quantity: +quantity};
    this.transactionService.saveTransaction(transaction)
      .subscribe(transaction => console.log("The added transaction: ", transaction));
  }
}
