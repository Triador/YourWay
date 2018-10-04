import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { BookComponent } from '../book/book.component';
import { LoginComponent } from '../login/login.component';
import { SignupComponent} from '../signup/signup.component'
import { SearchComponent} from '../search/search.component'

const routes: Routes = [
	{ path: 'books', component: BookComponent },
	{ path: 'login', component: LoginComponent},
	{ path: 'signup', component: SignupComponent},
	{ path: '', redirectTo: '/login', pathMatch: 'full'},
	{ path: 'search', component: SearchComponent}
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