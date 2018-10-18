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

	public getBooks() {
		return this.http.get<Book[]>(this.bookUrl);
	}

	public getBook(id: number) {
		return this.http.get<Book>(this.bookUrl + "/" + id);
	}

	public getBookTitles(titlePiece: string) {
		return this.http.get<Book[]>(this.bookSearchUrl + "/" + titlePiece);
	}

	public deleteBook(book: Book) {
		return this.http.delete(this.bookUrl + "/" + book.id);
	}

	public createBook(book: Book) {
		return this.http.post(this.bookUrl, book);
	}
}