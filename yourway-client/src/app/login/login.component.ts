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
export class LoginComponent {

  constructor(private router: Router, 
    private authService: AuthService, 
    private token: TokenStorage,
    private header: HeaderComponent) { }

  username: string;
  password: string;

  login(): void {
    this.authService.attemptAuth(this.username, this.password).subscribe(
      data => {
        this.token.saveToken(data.token);
        this.router.navigate(['books']);
        this.header.userLoggedIn = true;
      });
  }

}