import { Component } from '@angular/core';

import { MatDialog } from '@angular/material';

@Component({
  selector: 'app-add-note-dialog',
  templateUrl: './add-note-dialog.component.html',
  styleUrls: ['./add-note-dialog.component.css']
})
export class AddNoteDialogComponent {

  constructor(private dialog: MatDialog) { }

  openDialog() {
  	const dialogRef = this.dialog.open(AddNoteDialog);

  	dialogRef.afterClosed().subscribe(result => {
  		console.log('Dialog result: ${result}');
  	})
  }
}

@Component({
 	selector: 'add-note-dialog',
 	templateUrl: 'add-note-dialog.html',
 })
 export class AddNoteDialog {}
