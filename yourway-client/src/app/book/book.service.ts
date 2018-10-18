import { Injectable } from '@angular/core'
import { HttpClient, HttpHeaders } from '@angular/common/http'

import { Book } from '../models/book.model';

const httpOptions = {
	headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class BookService {

	constructor(private http: HttpClient) {}

	private bookUrl = 'http://localhost:8080/books';
	private bookSearchUrl = 'http://localhost:8080/books/search';

	public getBook(russianTitle: string) {
		return this.http.get<Book>(this.bookUrl + "/" + russianTitle);
	}

	public getBooks() {
		return this.http.get<Book[]>(this.bookUrl);
	}

	public getBookTitles(titlePiece) {
		console.log("inside getBookTitles")
		return this.http.get<string[]>(this.bookSearchUrl + "/" + titlePiece);
	}

	public deleteBook(book) {
		return this.http.delete(this.bookUrl + "/" + book.id);
	}

	public createBook(book) {
		return this.http.post(this.bookUrl, book);
	}
}