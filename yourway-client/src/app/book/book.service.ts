import { Injectable } from '@angular/core'
import { HttpClient, HttpParams } from '@angular/common/http'

import { Book } from '../models/book.model';
import { BookTitle } from '../models/book-title.model';
import { UserBook } from '../models/user-book.model';
import { ShortBookDescription } from '../models/short-book-description.model';

import { ProfileService } from '../profile/profile.service';

const bookUrl = 'http://localhost:8080/books';
const bookSearchUrl = 'http://localhost:8080/books/search';

@Injectable()
export class BookService {

	constructor(private http: HttpClient,
		private profileService: ProfileService) {}

	public getBook(bookId: string) {
		const userId: number = this.profileService.getUserId();
		return this.http.get<Book>(bookUrl + "/" + bookId, { 
			params: new HttpParams().set('userId', userId.toString())
		});
	}

	public getBooks() {
		return this.http.get<ShortBookDescription[]>(bookUrl);
	}

	public getBookTitles(titlePiece) {
		console.log("inside getBookTitles")
		return this.http.get<BookTitle[]>(bookSearchUrl + "/" + titlePiece);
	}

	public deleteBook(book) {
		return this.http.delete(bookUrl + "/" + book.bookId);
	}

	public createBook(book) {
		return this.http.post(bookUrl, book);
	}
}