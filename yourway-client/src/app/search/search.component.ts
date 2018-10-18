import { Component, OnInit, Input } from '@angular/core';

import { Book } from '../models/book.model';
import { BookService } from '../book/book.service';

// import { MatListModule } from '@angular/material/list';

@Component({
	selector: 'app-search',
	templateUrl: './search.component.html',
	styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
	displayedColumns = ["title"];
	searchActive: boolean = false;
	books: Book[] = [];

	constructor(private bookService: BookService) { }

	ngOnInit() {
	}

	displaySearchHintComponent(titlePiece: string): void {
		if (titlePiece) {
			this.bookService.getBookTitles(titlePiece)
			.subscribe( data => {
				this.books = data;
				console.log(this.books);
			});
		} else {
			this.books = [];
		}
	}

	searchOn(): void {
		this.searchActive = true;
	}

	searchOff(): void {
		this.searchActive = false;
	}

}
