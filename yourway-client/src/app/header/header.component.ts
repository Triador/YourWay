import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { TokenStorage } from '../core/token.storage';
import { AuthService } from '../core/auth.service';
import { ProfileService } from '../profile/profile.service';

@Component({
	selector: 'app-header',
	templateUrl: './header.component.html',
	styleUrls: ['./header.component.css']
})
export class HeaderComponent {

	constructor(private token: TokenStorage,
		private router: Router,
		private authService: AuthService,
		private profileService: ProfileService) { }

	logout(): void {
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

  	openProfile(): void {
  		const userId = this.profileService.getUserId();
  		this.router.navigate(['profile/' + userId]);
  	}
}
