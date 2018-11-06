import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
// import { MatDialog } from '@angular/material';
import { AuthService } from '../core/auth.service';
import { TokenStorage } from '../core/token.storage';
import { HeaderComponent } from '../header/header.component'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, 
    private authService: AuthService, 
    private token: TokenStorage,
    private header: HeaderComponent) { }

  username: string;
  password: string;

  ngOnInit() {
    console.log("login ngOnInit invoke, the value of userLoggedIn is " + this.authService.getLoggedIn());
    // if (this.authService.userLoggedIn) {
    //   this.router.navigate(['books']);
    // }
  }

  login(): void {
    this.authService.attemptAuth(this.username, this.password).subscribe(
      data => {
        this.token.saveToken(data.token);
        this.router.navigate(['books']);
        this.authService.setLoggedIn('true');
      });
  }

}