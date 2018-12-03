import '../../polyfills';

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
  MatIconModule,
  MatTabsModule,
  MatProgressBarModule,
  MatTooltipModule
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
  MatIconModule,
  MatTabsModule,
  MatProgressBarModule,
  MatTooltipModule],
  exports: [CommonModule, 
  MatToolbarModule, 
  MatButtonModule, 
  MatCardModule, 
  MatInputModule, 
  MatDialogModule, 
  MatTableModule, 
  MatProgressSpinnerModule,
  MatMenuModule,
  MatIconModule,
  MatTabsModule,
  MatProgressBarModule,
  MatTooltipModule],
})
export class CustomMaterialModule { }