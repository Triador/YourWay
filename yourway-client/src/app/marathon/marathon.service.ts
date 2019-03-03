import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Marathon } from '../models/marathon.model';

const marathonUrl = 'http://localhost:8080/marathons';

@Injectable()
export class MarathonService {

	constructor(private http: HttpClient) {}

	addMarathon(marathon: Marathon) {
		return this.http.post<Marathon>(marathonUrl, marathon);
	}

	getMarathons() {
		return this.http.get<Marathon[]>(marathonUrl);
	}

	getMarathon(marathonId: string) {
		return this.http.get<Marathon>(marathonUrl + '/' + marathonId);
	}
}