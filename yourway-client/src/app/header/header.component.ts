import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { TokenStorage } from '../core/token.storage';

@Component({
	selector: 'app-header',
	templateUrl: './header.component.html',
	styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
	@Input() signUp: boolean = true;
	userLoggedIn: boolean = false;

	constructor(private token: TokenStorage,
		private router: Router) { }

	ngOnInit() {
	}

	logout() {
		this.router.navigate(['login']);
		this.token.signOut();
		this.userLoggedIn = false;
	}

	login(): void {
		this.router.navigate(['login']);
	}

	signup(): void {
  		this.router.navigate(['signup']);
  	}
}
