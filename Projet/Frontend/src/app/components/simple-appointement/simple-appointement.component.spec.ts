import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SimpleAppointementComponent } from './simple-appointement.component';

describe('SimpleAppointementComponent', () => {
  let component: SimpleAppointementComponent;
  let fixture: ComponentFixture<SimpleAppointementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SimpleAppointementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SimpleAppointementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
