import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';

import { AddMarathonDialogComponent } from '../add-marathon-dialog/add-marathon-dialog.component';

@Component({
	selector: 'app-marathon',
	templateUrl: './marathon.component.html',
	styleUrls: ['./marathon.component.css']
})
export class MarathonComponent implements OnInit {

	constructor(private dialog: MatDialog) { }

	ngOnInit() {
	}

	addMarathon() {
		const dialogRef = this.dialog.open(AddMarathonDialogComponent);

		dialogRef.afterClosed().subscribe(data => {
			console.log(data);
		})
	}
}
