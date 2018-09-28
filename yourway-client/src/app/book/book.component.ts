import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material';
import { AuthService } from '../core/auth.service';
import { TokenStorage } from '../core/token.storage';

import { Book } from '../models/book.model';
import { SearchHintComponent } from '../search-hint/search-hint.component';
import { BookService } from './book.service';

@Component({
	selector: 'app-book',
	templateUrl: './book.component.html',
	styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
	displayedColumns = ['name', 'delete'];
	dataSource = new MatTableDataSource<Book>();
	title: string = "";
	displaySearchHint: boolean = false;

	constructor(private router: Router, 
		private bookService: BookService, 
		private authService: AuthService, 
		private token: TokenStorage,
		private searchHintComponent: SearchHintComponent) {
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

	logout() {
		this.router.navigate(['login']);
		this.token.signOut();
	}

	displaySearchHintComponent(titlePiece: string): void {
		this.searchHintComponent.saveDataToDataSource(this.bookService.getBookTitles(titlePiece));
	}

	d
}
