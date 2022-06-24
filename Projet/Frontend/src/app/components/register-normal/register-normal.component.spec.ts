import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterNormalComponent } from './register-normal.component';

describe('RegisterNormalComponent', () => {
  let component: RegisterNormalComponent;
  let fixture: ComponentFixture<RegisterNormalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterNormalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterNormalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
