import { Injectable } from '@angular/core'
import { HttpClient, HttpParams } from '@angular/common/http'

import { Book } from '../models/book.model';
import { BookTitle } from '../models/book-title.model';
import { UserBook } from '../models/user-book.model';
import { ShortBookDescription } from '../models/short-book-description.model';

import { ProfileService } from '../profile/profile.service';

@Injectable()
export class BookService {

	constructor(private http: HttpClient,
		private profileService: ProfileService) {}

	private bookUrl = 'http://localhost:8080/books';
	private bookSearchUrl = 'http://localhost:8080/books/search';

	public getBook(bookId: number) {
		const userId: number = this.profileService.getUserId();
		return this.http.get<Book>(this.bookUrl + "/" + bookId, { 
			params: new HttpParams().set('userId', userId.toString())
		});
	}

	public 

	public getBooks() {
		return this.http.get<ShortBookDescription[]>(this.bookUrl);
	}

	public getBookTitles(titlePiece) {
		console.log("inside getBookTitles")
		return this.http.get<BookTitle[]>(this.bookSearchUrl + "/" + titlePiece);
	}

	public deleteBook(book) {
		return this.http.delete(this.bookUrl + "/" + book.bookId);
	}

	public createBook(book) {
		return this.http.post(this.bookUrl, book);
	}
}