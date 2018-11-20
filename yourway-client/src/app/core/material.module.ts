import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  MatButtonModule, 
  MatCardModule, 
  MatDialogModule, 
  MatInputModule, 
  MatTableModule, 
  MatToolbarModule, 
  MatProgressSpinnerModule,
  MatMenuModule,
  MatIconModule
} from '@angular/material';

@NgModule({
  imports: [CommonModule, 
  MatToolbarModule, 
  MatButtonModule, 
  MatCardModule, 
  MatInputModule, 
  MatDialogModule, 
  MatTableModule, 
  MatProgressSpinnerModule,
  MatMenuModule,
  MatIconModule],
  exports: [CommonModule, 
  MatToolbarModule, 
  MatButtonModule, 
  MatCardModule, 
  MatInputModule, 
  MatDialogModule, 
  MatTableModule, 
  MatProgressSpinnerModule,
  MatMenuModule,
  MatIconModule],
})
export class CustomMaterialModule { }