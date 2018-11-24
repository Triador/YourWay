import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { switchMap } from 'rxjs/operators';

import { ProfileService } from './profile.service';
import { Profile } from '../models/profile.model';


@Component({
	selector: 'app-profile',
	templateUrl: './profile.component.html',
	styleUrls: ['./profile.component.css', '../book/all-book.component.css']
})
export class ProfileComponent implements OnInit {

	profile: Profile = new Profile();

	constructor(private profileService: ProfileService,
		private route: ActivatedRoute,
		private router: Router) { }

	ngOnInit() {
		this.route.paramMap.pipe(
			switchMap((params: ParamMap) => 
				this.profileService.getProfile(Number(params.get('id'))))
			).subscribe(data => {
				this.profile = data;
				for (var i = 0; i < this.profile.books.length; i++) {
					this.profile.books[i].imageLink = "../../assets/book_images/small_" + this.profile.books[i].imageLink;
				}
			})
	}

	openBook(id: number): void {
		this.router.navigate(['books/' + id]);
	}
}
