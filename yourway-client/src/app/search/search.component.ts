import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';

import { BookTitle } from '../models/book-title.model';
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
	titles: BookTitle[] = [];

	constructor(private bookService: BookService,
		private router: Router) { }

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

	displayBook(id: number): void {
		this.router.navigate(['books/' + id]);
	}

}
