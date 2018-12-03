import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AllBookComponent } from '../book/all-book.component';
import { BookComponent } from '../book/book.component';
import { LoginComponent } from '../login/login.component';
import { SignupComponent} from '../signup/signup.component';
import { SearchComponent} from '../search/search.component';
import { ProfileComponent } from '../profile/profile.component';
import { MarathonComponent } from '../marathon/marathon.component';

const routes: Routes = [
	{ path: 'books', component: AllBookComponent },
	{ path: 'books/:id', component: BookComponent },
	{ path: 'login', component: LoginComponent },
	{ path: 'signup', component: SignupComponent },
	{ path: '', redirectTo: '/login', pathMatch: 'full' },
	{ path: 'search', component: SearchComponent },
	{ path: 'profile/:id', component: ProfileComponent },
	{ path: 'marathon', component: MarathonComponent }
];

@NgModule({
	imports: [
		RouterModule.forRoot(routes)
	],
	exports: [
		RouterModule
	],
	declarations: []
})
export class AppRoutingModule { }