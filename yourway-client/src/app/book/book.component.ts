import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';

import { Book } from '../models/book.model';
import { BookService } from './book.service';

@Component({
	selector: 'app-book',
	templateUrl: './book.component.html',
	styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

	book: Book = new Book();

	constructor(private bookService: BookService,
		private route: ActivatedRoute,
		private router: Router) {}

	ngOnInit() {
		this.route.paramMap.pipe(
			switchMap((params: ParamMap) =>
				this.bookService.getBook(params.get('id')))	
		).subscribe(data => {
			console.log(data);
			this.book = data;
		});
	}
}
