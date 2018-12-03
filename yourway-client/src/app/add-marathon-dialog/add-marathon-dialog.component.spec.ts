import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMarathonDialogComponent } from './add-marathon-dialog.component';

describe('AddMarathonDialogComponent', () => {
  let component: AddMarathonDialogComponent;
  let fixture: ComponentFixture<AddMarathonDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddMarathonDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddMarathonDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
