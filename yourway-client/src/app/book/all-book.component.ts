import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material';

import { Book } from '../models/book.model';
import { BookService } from './book.service';
import { BookComponent } from './book.component'

@Component({
	selector: 'app-all-book',
	templateUrl: './all-book.component.html',
	styleUrls: ['all-book.component.css']
})
export class AllBookComponent {
	books: Book[];

	constructor(private router: Router, private bookService: BookService, private bookComponent: BookComponent) {

	}

	ngOnInit() {
		this.bookService.getBooks()
			.subscribe( data => {
				for (var i = 0; i < data.length; i++) {
					data[i].imageLink = "../../assets/book_images/small_" + data[i].imageLink;
				}
				this.books = data;
			});
	}

	openBook(id: number): void {
		this.router.navigate(['books/' + id]);
	}
}