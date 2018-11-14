import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { User } from '../models/user.model';
import { SignUpService } from './signup.service';
import { AuthService } from '../core/auth.service';

@Component({
	selector: 'app-signup',
	templateUrl: './signup.component.html',
	styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
	user: User = new User();
	username: string;
	password: string;

	constructor(private router: Router, 
		private userService: SignUpService,
		private authService: AuthService) { }

	ngOnInit() {
		this.authService.setSignUp("false");
	}

	createUser(): void {
		this.user.name = this.username;
		this.user.password = this.password;
		this.userService.createUser(this.user)
		.subscribe( data => {
			alert("You are registered");
		});
	};

}
