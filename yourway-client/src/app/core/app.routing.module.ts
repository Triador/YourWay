import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { BookComponent } from '../book/book.component';
import { LoginComponent } from '../login/login.component';

const routes: Routes = [
	{ path: 'books', component: BookComponent },
	{ path: 'login', component: LoginComponent},
	{ path : '', component : LoginComponent }
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