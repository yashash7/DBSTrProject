import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiCallerService } from '../api-caller.service';

@Component({
  selector: 'app-receiver',
  templateUrl: './receiver.component.html',
  styleUrls: ['./receiver.component.css']
})
export class ReceiverComponent implements OnInit {
  receiverAccNo = ""
  receiverName = ""
  receiverBic = ""
  receiverBank = ""
  messageCode = ""
  amount!:number;
  terrorStatus = false
  receiverOk = true
  balanceStatus = false
  // cancel = false

  constructor(private http:HttpClient, private router:Router, private apiCaller:ApiCallerService) { }

  ngOnInit(): void {
    if(localStorage.getItem("authUser")== "negative" || localStorage.getItem("authUser")== null){
      alert("Please login")
      this.router.navigateByUrl("/login")
    }
  }

  receiverNameCheck():void {
    this.receiverName = this.receiverName.toUpperCase()
    let response = this.http.get(`http://localhost:9000/receiverNameCheck?name=${this.receiverName}`, {responseType:"text"})
    response.subscribe((data)=> {
      if(data=="RED") {
        this.terrorStatus = true // terrorStatus true means, receiver name is present in Terror List
        this.receiverOk = false // receiverOk status false means receiver is invalid cannot send amount
        // alert("Receiver Name is in 'OFAC' List; Please check!")
        // this.router.navigate(['/sender'])
        // .then(() => {
        //   window.location.reload();
        // });
      }
      else {  
        this.terrorStatus = false 
        this.receiverOk = true 
      }
    })
  }

  receiverBankByBicAuto():void {
    let response = this.http.get(`http://localhost:9000/receiverBank?bic=${this.receiverBic}`, {responseType:"text"})
    response.subscribe((data)=> {
      if(data!="RED") {
        this.receiverOk = true
        this.receiverBank=data
      }
      else {
        this.receiverOk = false
        this.receiverBic=""
        this.receiverBank = ""
        alert("Invalid BIC Code; Please Enter a Valid BIC Code")
      }
    })    
  }

  checkBalanceOrOd():void {
    if(this.amount<=0) {
      alert("Please Enter a Valid Amount")
    }
    else if(this.apiCaller.checkSenderBalanceOd(this.amount)==="GREEN") {
      this.balanceStatus = true
    }
    else {
      this.balanceStatus = false
    }
  }

  proceedToTransaction():void {
    if(this.receiverAccNo=="" || this.receiverBank=="" || this.receiverBic=="" || this.receiverName=="" || this.messageCode=="" || this.amount<=0)
    alert("Please Enter all the fields correctly"!)
    else {
      console.log(this.amount)
      this.apiCaller.setReceiverAccNo(this.receiverAccNo)
      this.apiCaller.setmessageCode(this.messageCode)
      if(this.receiverOk==true && this.terrorStatus==false && this.balanceStatus==true) {
        console.log("BalanceStatus1 "+this.balanceStatus)
        this.apiCaller.setStatusFlag("success")
        // this.router.navigate(['/transaction'])
        this.router.navigate(['/transaction'])
      }
      else {
        // this.cancel = true
        if(this.balanceStatus==false && this.terrorStatus==false) {
          console.log("BalanceStatus2 "+this.balanceStatus)
          this.apiCaller.setStatusFlag("insufficientFunds")
          // this.router.navigate(['/transaction'])
          this.router.navigate(['/transaction'])
        } 
        
        else if(this.terrorStatus==true && this.balanceStatus==true) {
          this.apiCaller.setStatusFlag("receiverInTerror")
          // this.router.navigateByUrl("/transaction")
          this.router.navigate(['/transaction'])
        }
      }
    }

  }
}