import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from './login/User';
import { Transaction } from './transaction/transaction.component';

@Injectable({
  providedIn: 'root'
})
export class ApiCallerService {

  user:User = {
    userName:"",
    password:""
  }
  
  senderObj: any
  amount = 0
  receiverAccNO = ""
  messageCode = ""
  amountValue = 0
  updatedBalance!: number
  missingFields: any
  transactionStatus = ""
  statusFlag = ""

  // senderTransList: Transaction[] = [];

  constructor(private http:HttpClient, private router:Router) { }

  public login(userName:string, password:string){
    localStorage.clear();
    this.user.userName=userName
    this.user.password=password
    let res = this.http.get("http://localhost:9000/auth?username="+this.user.userName+"&password="+this.user.password,{responseType:"text"})
    res.subscribe((data)=>{
      localStorage.setItem("authUser",data)
      if(localStorage.getItem("authUser") == "postive") {
        this.router.navigateByUrl("/forms")
      }else{
        alert("Invalid Crendentials")
        this.router.navigateByUrl("/login")
      }
    })
  }

  // Storing Sender Object at Sender Page Exit for future use
  setSenderObject(data:any):void {
    this.senderObj = data;
    // console.log(this.senderObj.name)
  }

  setReceiverAccNo(data:any):void {
    this.receiverAccNO = data
    // console.log("Receiver Acc No: "+this.receiverAccNO)
  }
  checkSenderBalanceOd(amount:number):any {
    this.amount = amount
    if(this.senderObj.overdraft=="yes") {
      return "GREEN"
    }
    else {
      if(this.senderObj.balance>=amount) {
        return "GREEN"
      }
      else {
        return "RED"
      }
    }
  }
  
  setmessageCode(code:string):void {
    this.messageCode = code
    // console.log(this.messageCode)
  }

  setStatusFlag(data:string):void {
    this.statusFlag = data
  }

  getTransactionStatus(data:string):void {
    this.transactionStatus = data
  }

  doTransaction():any {
    this.amountValue = this.amount
    // console.log("amountValue:"+this.amountValue)
    this.missingFields = [this. messageCode, this.amountValue, this.statusFlag, this.receiverAccNO]
    console.log("MissingFields:"+this.missingFields)
    let response = this.http.get(`http://localhost:9000/transaction?missing=${this.missingFields}`)
    response.subscribe((data)=> {
      localStorage.setItem("transList", JSON.stringify(Object.values(data)));
    })
  }

  setSenderTransList() {
    // console.log(localStorage.getItem("transList"))
  }


}