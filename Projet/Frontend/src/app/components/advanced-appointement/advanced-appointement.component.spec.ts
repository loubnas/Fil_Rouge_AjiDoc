import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvancedAppointementComponent } from './advanced-appointement.component';

describe('AdvancedAppointementComponent', () => {
  let component: AdvancedAppointementComponent;
  let fixture: ComponentFixture<AdvancedAppointementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdvancedAppointementComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdvancedAppointementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
