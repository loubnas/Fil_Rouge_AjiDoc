import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeMoComponent } from './liste-mo.component';

describe('ListeMoComponent', () => {
  let component: ListeMoComponent;
  let fixture: ComponentFixture<ListeMoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListeMoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListeMoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
