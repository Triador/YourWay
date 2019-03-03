import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Note } from '../models/note.model';

const bookUrl = "http://localhost:8080/books/"

@Injectable()
export class NoteService {

	constructor(private http: HttpClient) {}

	saveNote(note: Note) {
		return this.http.post<Note>(bookUrl + note.bookId + '/notes', note);
	}

	deleteNote(note: Note) {
		return this.http.delete(bookUrl + note.bookId + '/notes/' + note.noteId);
	}
}