import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material';

import { Book } from '../models/book.model';
import { BookService } from './book.service';

@Component({
	selector: 'app-book',
	templateUrl: './book.component.html',
	styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
	displayedColumns = ['name', 'delete'];
	dataSource = new MatTableDataSource<Book>();

	constructor(private router: Router, private bookService: BookService) {

	}

	ngOnInit() {
		this.bookService.getBooks()
			.subscribe( data => {
				this.dataSource.data = data;
			})
	};

	deleteBook(book: Book): void {
		this.bookService.deleteBook(book)
			.subscribe( data => {
				this.dataSource.data = this.dataSource.data.filter(b => b !== book);
			})
	};
}
