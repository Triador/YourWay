import { Component, OnInit } from '@angular/core';

import { ProfileService } from './profile.service';
import { Book } from '../models/book.model';
import { User } from '../models/user.model';

@Component({
	selector: 'app-profile',
	templateUrl: './profile.component.html',
	styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

	books: Book[];
	user: User;

	constructor(private profileService: ProfileService) { }

	ngOnInit() {

	}

}
