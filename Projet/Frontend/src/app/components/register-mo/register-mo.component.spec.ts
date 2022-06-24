import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterMOComponent } from './register-mo.component';

describe('RegisterMOComponent', () => {
  let component: RegisterMOComponent;
  let fixture: ComponentFixture<RegisterMOComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterMOComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterMOComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
