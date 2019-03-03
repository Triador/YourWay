import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { Router } from '@angular/router';

import { AddMarathonDialogComponent } from '../add-marathon-dialog/add-marathon-dialog.component';
import { MarathonService } from './marathon.service';
import { Marathon } from '../models/marathon.model';

@Component({
	selector: 'app-all-marathon',
	templateUrl: './all-marathon.component.html',
	styleUrls: ['./all-marathon.component.css']
})
export class AllMarathonComponent implements OnInit {

	marathons: Marathon[] = [];

	constructor(private dialog: MatDialog,
		private marathonService: MarathonService,
		private router: Router) { }

	ngOnInit() {
		this.marathonService.getMarathons()
			.subscribe(data => {
				this.marathons = data;
			})
	}

	addMarathon() {
		const dialogRef = this.dialog.open(AddMarathonDialogComponent);

		dialogRef.afterClosed().subscribe(dialogData => {
			this.marathonService.addMarathon(dialogData)
				.subscribe(data => {
					this.marathons.push(data);
				})
		})
	}

	openMarathon(marathonId: number) {
		this.router.navigate(['marathons/' + marathonId]);
	}
}
