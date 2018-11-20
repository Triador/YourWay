import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../core/auth.service';
import { ProfileService } from '../profile/profile.service';
import { TokenStorage } from '../core/token.storage';
import { HeaderComponent } from '../header/header.component'

import * as jwtDecode from 'jwt-decode';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router, 
    private authService: AuthService, 
    private token: TokenStorage,
    private header: HeaderComponent,
    private profileService: ProfileService) { }

  username: string;
  password: string;

  ngOnInit() {
    this.authService.setSignUp("true");
    if (this.authService.getLoggedIn() === 'true') {
      this.router.navigate(['books']);
    }
  }

  login(): void {
    this.authService.attemptAuth(this.username, this.password).subscribe(
      data => {
        this.token.saveToken(data.token);
        this.router.navigate(['books']);
        this.authService.setLoggedIn('true');

        const decodedToken = jwtDecode(data.token);
        console.log(JSON.stringify(decodedToken);

        this.profileService.setUsername(this.username);
      });
  }

}