import { Component, OnInit } from '@angular/core';
import { ApiCallerService } from '../api-caller.service';
import { User } from './User';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user:User = {
    "userName":"",
    "password":""
  }

  constructor(private apiCaller:ApiCallerService) { }

  ngOnInit(): void {
  }

  public loginAuth() {
    var today = new Date();
    var d = String(today).substring(0,3);
    if(d == "Sun" || d == "Sat"){
      document.write("Saturday and Sunday bank is closed");
    }
    else {
      this.apiCaller.login(this.user.userName,this.user.password)
    }
  }

  /* ---------- */

  

  /* ---------- */

}