import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material';

import { Book } from '../models/book.model';
import { BookService } from './book.service';
import { BookComponent } from './book.component'

@Component({
	selector: 'app-add-book',
	templateUrl: './add-book.component.html',
	styleUrls: ['add-book.component.css']
})
export class AddBookComponent {
	book: Book = new Book();

	constructor(private router: Router, private bookService: BookService, private bookComponent: BookComponent) {

	}

	createBook(): void {
		this.bookService.createBook(this.book)
			.subscribe( data => {
				this.bookComponent.ngOnInit();
			});
	};
}