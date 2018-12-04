import { Component, OnInit } from '@angular/core';

import { Marathon } from '../models/marathon.model';

@Component({
	selector: 'app-add-marathon-dialog',
	templateUrl: './add-marathon-dialog.component.html',
	styleUrls: ['./add-marathon-dialog.component.css']
})
export class AddMarathonDialogComponent implements OnInit {

	marathon: Marathon = new Marathon();

	constructor() { }

	ngOnInit() {
	}

}
