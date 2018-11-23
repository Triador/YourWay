import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';

import { Book } from '../models/book.model';
import { BookService } from './book.service';
import { ProfileService } from '../profile/profile.service';

@Component({
	selector: 'app-book',
	templateUrl: './book.component.html',
	styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

	book: Book = new Book();
	columnsToDisplay = ["fieldNames"];
	isDisabled: boolean = false;

	constructor(private bookService: BookService,
		private route: ActivatedRoute,
		private router: Router,
		private profileService: ProfileService) {}

	ngOnInit() {
		this.route.paramMap.pipe(
			switchMap((params: ParamMap) => 
				this.bookService.getBook(Number(params.get('id')))
		).subscribe(data => {
			console.log(data);
			data.imageLink = "../../assets/book_images/small_" + data.imageLink;
			this.book = data;
			this.isDisabled = data.disable;
		});
	}

	addBookToProfile() {
		this.isDisabled = true;
		this.profileService.addBookToProfile(this.book.bookId);
	}
}
