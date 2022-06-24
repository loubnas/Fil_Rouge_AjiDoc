import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateMoComponent } from './update-mo.component';

describe('UpdateMoComponent', () => {
  let component: UpdateMoComponent;
  let fixture: ComponentFixture<UpdateMoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateMoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateMoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
