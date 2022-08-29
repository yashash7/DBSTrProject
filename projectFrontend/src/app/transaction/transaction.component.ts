import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiCallerService } from '../api-caller.service';

export interface Transaction {
  transactionId: string;
  // senderAccNo: string;
  // senderName: string;
  receiverAccNo: string;
  receiverName:string;
  receiverBIC: string;
  bankName: string;
  messageCode: string;
  messageInstruction: string;
  status: string;
  date: string;
}

// const ELEMENT_DATA: Transaction[] = [];


@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit {
  displayedColumns: string[] = ['transactionId', 'receiverAccNo', 'receiverName', 'receiverBIC', 'bankName', 'amount', 'messageCode', 'messageInstruction', 'status', 'date'];
  dataSource: any;

  constructor(private apiCaller:ApiCallerService, private router:Router) { }

  ngOnInit(): void {
    this.apiCaller.doTransaction();
    this.dataSource = localStorage.getItem("transList");
    this.dataSource = JSON.parse(this.dataSource);
  }

  goToForms() {
    this.router.navigateByUrl("/forms")
  }

}