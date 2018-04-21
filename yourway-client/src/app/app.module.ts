import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { BookComponent } from './book/book.component';
import { AppRoutingModule } from './app.routing.module';
import { BookService } from './book/book.service';
import { HttpClientModule } from '@angular/common/http'
import { AddBookComponent } from './book/add-book.component';

@NgModule({
  declarations: [
    AppComponent,
    BookComponent,
    AddBookComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [BookService],
  bootstrap: [AppComponent]
})
export class AppModule { }
