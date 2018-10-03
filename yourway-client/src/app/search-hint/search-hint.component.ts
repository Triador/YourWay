import { Component, OnInit, Input } from '@angular/core';

import { Book } from '../models/book.model';

// import { MatListModule } from '@angular/material/list';

@Component({
	selector: 'app-search-hint',
	templateUrl: './search-hint.component.html',
	styleUrls: ['./search-hint.component.css']
})
export class SearchHintComponent implements OnInit {
	displayedColumns = ["title"];
	@Input() public bookTitles;
	@Input() public bookSearchActive;

	constructor() { }

	ngOnInit() {
	}

}
