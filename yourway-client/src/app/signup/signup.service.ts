import { Injectable } from '@angular/core'
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Router } from '@angular/router';

import { User } from '../models/user.model'

const httpOptins = {
	headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable()
export class SignUpService {

	constructor(private router: Router, private http: HttpClient) {}

	private signupUrl = 'http://localhost:8080/users/signup'

	public createUser(user) {
		this.router.navigate(['login']);
		return this.http.post(this.signupUrl, user);
	}
}