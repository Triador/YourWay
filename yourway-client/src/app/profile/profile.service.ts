import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

const USER_ID = 'USER_ID';
const profileUrl = 'http://localhost:8080/profiles';

@Injectable()
export class ProfileService {

	constructor(private http: HttpClient) {}

	setUserId(username: string) {
		window.localStorage.setItem(USER_ID, username);
	}

	getUserId() {
		window.localStorage.getItem(USER_ID);
	}

	addBookToProfile(bookId: number) {
		const userId = this.getUserId();
		const params = new HttpParams()
			.set('userId', userId)
			.set('bookId', bookId);

		this.http.post(profileUrl, {params}).subscribe(response => console.log(response));
	}
}