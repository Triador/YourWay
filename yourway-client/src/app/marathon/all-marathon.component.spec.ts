import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllMarathonComponent } from './all-marathon.component';

describe('MarathonComponent', () => {
  let component: AllMarathonComponent;
  let fixture: ComponentFixture<AllMarathonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllMarathonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllMarathonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
