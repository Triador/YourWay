import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { TokenStorage } from '../core/token.storage';
import { AuthService } from '../core/auth.service';

@Component({
	selector: 'app-header',
	templateUrl: './header.component.html',
	styleUrls: ['./header.component.css']
})
export class HeaderComponent {

	constructor(private token: TokenStorage,
		private router: Router,
		private authService: AuthService) { }

	logout() {
		this.router.navigate(['login']);
		this.token.signOut();
		this.authService.setLoggedIn('false');
	}

	login(): void {
		this.router.navigate(['login']);
		this.authService.setSignUp('true');
	}

	signup(): void {
  		this.router.navigate(['signup']);
  		this.authService.setSignUp('false');
  	}
}
