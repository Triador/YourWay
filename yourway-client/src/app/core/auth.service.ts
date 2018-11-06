import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const LOGGED_IN = 'LoggedIn';
const SIGN_UP = 'signUp';

@Injectable()
export class AuthService {

	baseUrl: 'http://localhost:8080/auth';

	constructor(private http: HttpClient) {
	}

	setSignUp(value: string) {
		window.localStorage.setItem(SIGN_UP, value);
	}

	getSignUp() {
		return window.localStorage.getItem(SIGN_UP);
	}

	setLoggedIn(value: string) {
		window.localStorage.setItem(LOGGED_IN, value);
	}

	getLoggedIn() {
		return window.localStorage.getItem(LOGGED_IN);
	}

	attemptAuth(ussername: string, password: string): Observable<any> {
		const credentials = {username: ussername, password: password};
		console.log('attempAuth ::');
		return this.http.post<any>('http://localhost:8080/token/generate-token', credentials);
	}

}