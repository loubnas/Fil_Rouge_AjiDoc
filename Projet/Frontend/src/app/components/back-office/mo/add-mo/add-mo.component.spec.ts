import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMoComponent } from './add-mo.component';

describe('AddMoComponent', () => {
  let component: AddMoComponent;
  let fixture: ComponentFixture<AddMoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddMoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddMoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
