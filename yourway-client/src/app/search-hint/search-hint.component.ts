import { Component, OnInit } from '@angular/core';

import { Book } from '../models/book.model';

import { MatTableDataSource } from '@angular/material';

@Component({
	selector: 'app-search-hint',
	templateUrl: './search-hint.component.html',
	styleUrls: ['./search-hint.component.css']
})
export class SearchHintComponent implements OnInit {
	dataSource = new MatTableDataSource<string>();

	constructor() { }

	ngOnInit() {
	}

	saveDataToDataSource(observable) {
		observable.subscribe( data => {
				this.dataSource.data = data;
			})
	}

}
