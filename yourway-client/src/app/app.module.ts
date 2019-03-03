import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CustomMaterialModule } from './core/material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';
import { AppComponent } from './app.component';
import { BookComponent } from './book/book.component';
import { AppRoutingModule } from './core/app.routing.module';
import { BookService } from './book/book.service';
import { NoteService } from './book/note.service';
import { MarathonService } from './marathon/marathon.service';
import { SignUpService } from './signup/signup.service';
import { AllBookComponent } from './book/all-book.component';
import { LoginComponent } from './login/login.component';
import { ErrorDialogComponent } from './core/error-dialog.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { AuthService } from './core/auth.service';
import { ProfileService } from './profile/profile.service';
import { Interceptor } from './core/inteceptor';
import { TokenStorage } from "./core/token.storage";
import { SignupComponent } from './signup/signup.component';
import { SearchComponent } from './search/search.component';
import { MatListModule } from '@angular/material/list';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { ProfileComponent } from './profile/profile.component';
import { AddNoteDialogComponent } from './add-note-dialog/add-note-dialog.component';
import { AllMarathonComponent } from './marathon/all-marathon.component';
import { AddMarathonDialogComponent } from './add-marathon-dialog/add-marathon-dialog.component';
import { MarathonComponent } from './marathon/marathon.component';

@NgModule({
  declarations: [
    AppComponent,
    BookComponent,
    AllBookComponent,
    LoginComponent,
    ErrorDialogComponent,
    SignupComponent,
    SearchComponent,
    HeaderComponent,
    FooterComponent,
    ProfileComponent,
    AddNoteDialogComponent,
    AllMarathonComponent,
    AddMarathonDialogComponent,
    MarathonComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    CustomMaterialModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatListModule,
    FlexLayoutModule
  ],
  entryComponents: [ErrorDialogComponent, AddNoteDialogComponent, AddMarathonDialogComponent],
  providers: [ErrorDialogComponent, BookService, AuthService, TokenStorage, SignUpService,
    SearchComponent, HeaderComponent, BookComponent, ProfileService, AddNoteDialogComponent,
    AddMarathonDialogComponent, NoteService, MarathonService,
    {provide: HTTP_INTERCEPTORS,
    useClass: Interceptor,
    multi : true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
