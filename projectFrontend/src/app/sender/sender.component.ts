import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiCallerService } from '../api-caller.service';

@Component({
  selector: 'app-sender',
  templateUrl: './sender.component.html',
  styleUrls: ['./sender.component.css']
})
export class SenderComponent implements OnInit {
  senderAccNo: string = "";
  senderName: string = "";
  acnoValid = false
  cancel = false
  user: any;

  constructor(private http:HttpClient, private router:Router, private apiCaller:ApiCallerService) { }

  ngOnInit(): void {
    // localStorage.clear();
    if(localStorage.getItem("authUser")== "negative" || localStorage.getItem("authUser")== null){
      alert("Please login")
      this.router.navigateByUrl("/login")
    }
    else {
      this.user = localStorage.getItem("authUser")
      localStorage.clear();
      localStorage.setItem("authUser", this.user)
    }
  }

  onlyDigits(str:string) {
    return /^[0-9]+$/.test(str);
  }

  validateSender():void {
    if(this.senderAccNo.length==14 && this.onlyDigits(this.senderAccNo)) {
      let response = this.http.get(`http://localhost:9000/validateSender?accno=${this.senderAccNo}`, {responseType:"text"})
      response.subscribe((data)=> {
        
        if(data=="RED" || data.length<=1) {
          this.acnoValid=false
          this.apiCaller.setStatusFlag("accno")
          this.senderAccNo = ""
          this.senderName=""
          alert("Invalid Sender! Please Enter Valid sender Account No")
        }
        else {
          this.senderName = "WELCOME "+data
          this.acnoValid=true
          // console.log("SenderName in valsend: "+this.senderName)
          localStorage.setItem("senderAccNo", this.senderAccNo);
          localStorage.setItem("senderName", this.senderName);
        }     
      })
    }
    else {
      this.acnoValid = false
      this.apiCaller.setStatusFlag("accno")
      this.senderAccNo = ""
      this.senderName = ""
      // alert("Please Enter Valid Account Number");
      alert("Invalid Sender! Please Enter Valid sender Account No")
    }
  }

  proceedToReceiver():void {
    if(this.senderAccNo!=undefined && this.acnoValid==true) {
      localStorage.setItem("sender","proceed")
      // console.log(localStorage.getItem("sender"))
      let senderObj = this.http.get(`http://localhost:9000/getSender?accno=${this.senderAccNo}`)
      senderObj.subscribe((data)=>this.apiCaller.setSenderObject(data))        
      this.router.navigate(['/receiver'])
    } 
    else {
      this.apiCaller.setStatusFlag("accno")
      this.acnoValid=false
      this.senderAccNo = ""
      this.senderName = ""
      alert("Please Enter a Valid Account Number!")
    }
  }
    
}
