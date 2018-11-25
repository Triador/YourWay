import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { animate, state, style, transition, trigger } from '@angular/animations';

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
				for (var i = 0; i < this.profile.progressBooks.length; i++) {
					this.profile.progressBooks[i].imageLink = "../../assets/book_images/small_" + this.profile.progressBooks[i].imageLink;
				}
				for (var i = 0; i < this.profile.futureBooks.length; i++) {
					this.profile.futureBooks[i].imageLink = "../../assets/book_images/small_" + this.profile.futureBooks[i].imageLink;
				}
				for (var i = 0; i < this.profile.finishedBooks.length; i++) {
					this.profile.finishedBooks[i].imageLink = "../../assets/book_images/small_" + this.profile.finishedBooks[i].imageLink;
				}
			})
	}

	openBook(id: number): void {
		this.router.navigate(['books/' + id]);
	}
}
