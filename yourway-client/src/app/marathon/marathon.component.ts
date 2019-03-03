import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { switchMap } from 'rxjs/operators';

import { MarathonService } from './marathon.service';
import { Marathon } from '../models/marathon.model';

@Component({
	selector: 'app-marathon',
	templateUrl: './marathon.component.html',
	styleUrls: ['./marathon.component.css']
})
export class MarathonComponent implements OnInit {

	marathon: Marathon;

	constructor(private route: ActivatedRoute,
		private router: Router,
		private marathonService: MarathonService) { }

	ngOnInit() {
		this.route.paramMap.pipe(
			switchMap((params: ParamMap) =>
				this.marathonService.getMarathon(params.get('id')))
			).subscribe(data => {
				this.marathon = data;
			})
	}
}
