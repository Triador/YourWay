import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { UserBook } from '../models/user-book.model';
import { Profile } from '../models/profile.model';

const USER_ID = 'USER_ID';
const profileUrl = 'http://localhost:8080/profiles';

@Injectable()
export class ProfileService {

	constructor(private http: HttpClient) {}

	setUserId(username: string) {
		window.localStorage.setItem(USER_ID, username);
	}

	getUserId(): number {
		return Number(window.localStorage.getItem(USER_ID));
	}

	addBookToProfile(bookId: number, bookStatus: string) {
		const userBook: UserBook = new UserBook();
		userBook.userId = this.getUserId();
		userBook.bookId = bookId;
		userBook.bookStatus = bookStatus;
		this.http.post(profileUrl, userBook).subscribe(response => console.log(response));
	}

	getProfile(userId: number) {
		return this.http.get<Profile>(profileUrl + '/' + userId);
	}

	deleteBook(bookId: number) {
		const userId: number = this.getUserId();
		return this.http.request('delete', profileUrl, { 
			params: new HttpParams()
				.set('userId', userId.toString())
				.set('bookId', bookId.toString())
		});
	}
}