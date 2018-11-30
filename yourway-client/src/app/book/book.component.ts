import { Component, OnInit } from '@angular/core';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';

import { Book } from '../models/book.model';
import { BookService } from './book.service';
import { Note } from '../models/note.model';
import { ProfileService } from '../profile/profile.service';
import { NoteService } from '../book/note.service';
import { AddNoteDialogComponent } from '../add-note-dialog/add-note-dialog.component';

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
		private profileService: ProfileService,
		private noteService: NoteService,
		private dialog: MatDialog) {}

	ngOnInit() {
		this.route.paramMap.pipe(
			switchMap((params: ParamMap) => 
				this.bookService.getBook(Number(params.get('id'))))
			).subscribe(data => {
				console.log(data);
				data.imageLink = "../../assets/book_images/small_" + data.imageLink;
				this.book = data;
				this.isDisabled = data.disable;
			});
	}

	addBookToProfile(bookStatus: string) {
			this.isDisabled = true;
			this.profileService.addBookToProfile(this.book.bookId, bookStatus);
	}

	openNoteDialog() {
			const dialogRef = this.dialog.open(AddNoteDialogComponent);

			dialogRef.afterClosed().subscribe(noteText => {
				this.saveNote(noteText)
			})
	}

	saveNote(noteText: string) {
		const note: Note = new Note();
		note.bookId = this.book.bookId;
		note.text = noteText;
		this.noteService.saveNote(note)
			.subscribe(data => {
				this.book.notes.push(data);
			});
	}
}
