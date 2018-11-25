import { Book } from './book.model';

export class Profile {
	username: string;
	imageLink: string;
	progressBooks: Book[];
	futureBooks: Book[];
	finishedBooks: Book[];
}