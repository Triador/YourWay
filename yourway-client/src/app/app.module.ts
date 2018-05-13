import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CustomMaterialModule } from './core/material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppComponent } from './app.component';
import { BookComponent } from './book/book.component';
import { AppRoutingModule } from './core/app.routing.module';
import { BookService } from './book/book.service';
import { SignUpService } from './signup/signup.service';
import { AddBookComponent } from './book/add-book.component';
import { LoginComponent } from './login/login.component';
import { ErrorDialogComponent } from './core/error-dialog.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { AuthService } from "./core/auth.service";
import { Interceptor } from "./core/inteceptor";
import { TokenStorage } from "./core/token.storage";
import { SignupComponent } from './signup/signup.component';

@NgModule({
  declarations: [
    AppComponent,
    BookComponent,
    AddBookComponent,
    LoginComponent,
    ErrorDialogComponent,
    SignupComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    CustomMaterialModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  entryComponents: [ErrorDialogComponent],
  providers: [ErrorDialogComponent, BookService, AuthService, TokenStorage, SignUpService,
    {provide: HTTP_INTERCEPTORS,
    useClass: Interceptor,
    multi : true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
