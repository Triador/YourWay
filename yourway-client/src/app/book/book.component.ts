import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Book } from '../models/book.model'
import { BookService } from './book.service'

@Component({
	selector: 'app-book',
	templateUrl: './book.component.html',
	styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

	books: Book[];

	constructor(private router: Router, private bookService: BookService) {

	}

	ngOnInit() {
		this.bookService.getBooks()
			.subscribe( data => {
				this.books = data;
			})
	};

	deleteBook(book: Book): void {
		this.bookService.deleteBook(book)
			.subscribe( data => {
				this.books = this.books.filter(b => b !== book);
			})
	};
}
