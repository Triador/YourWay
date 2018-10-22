import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';

import { Book } from '../models/book.model';
import { BookService } from './book.service';

@Component({
	selector: 'app-book',
	templateUrl: './book.component.html',
	styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

	book: Book;

	constructor(private bookService: BookService) {
	}

	ngOnInit() {
	};
}
