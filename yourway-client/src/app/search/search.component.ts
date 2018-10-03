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
	titles: string[] = [];

	constructor(private bookService: BookService) { }

	ngOnInit() {
	}

	displaySearchHintComponent(titlePiece: string): void {
		if (titlePiece) {
			this.bookService.getBookTitles(titlePiece)
			.subscribe( data => {
				this.titles = data;
				console.log(this.titles);
			});
		} else {
			this.titles = [];
		}
	}

	searchOn(): void {
		this.searchActive = true;
	}

	searchOff(): void {
		this.searchActive = false;
	}

}
