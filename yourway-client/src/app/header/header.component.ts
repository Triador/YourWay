import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { TokenStorage } from '../core/token.storage';

@Component({
	selector: 'app-header',
	templateUrl: './header.component.html',
	styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
	signUp: boolean = true;
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
		console.log("before login " + this.signUp);
		this.router.navigate(['login']);
		console.log("login after navigate " + this.signUp);
		this.signUp = true;
		console.log("login after assignment " + this.signUp);
	}

	signup(): void {
		console.log("before signup " + this.signUp);
  		this.router.navigate(['signup']);
  		console.log("signup after navigate " + this.signUp);
  		this.signUp = false;
  		console.log("signup after assignment " + this.signUp);
  	}
}
