import { Note } from '../models/note.model';

export class Book {
	bookId: number;
    title: string;
    author: string;
    pageAmount: number;
    publicationYear: number;
    isbn: string;
    description: string;
    imageLink: string;
    disable: boolean;
    notes: Note[];
}