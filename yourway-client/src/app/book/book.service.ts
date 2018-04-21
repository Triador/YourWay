import { Injectable } from '@angular/core'
import { HttpClient, HttpHeaders } from '@angular/common/http'

import { Book } from '../models/book.model';

const httpOptions = {
	headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class BookService {

	constructor(private http: HttpClient) {}

	private bookUrl = 'http://localhost:8080/api'

	public getBooks() {
		return this.http.get<Book[]>(this.bookUrl);
	}

	public deleteBook(book) {
		return this.http.delete(this.bookUrl + "/" + book.id);
	}

	public createBook(book) {
		return this.http.post(this.bookUrl, book);
	}
}