import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteMoComponent } from './delete-mo.component';

describe('DeleteMoComponent', () => {
  let component: DeleteMoComponent;
  let fixture: ComponentFixture<DeleteMoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteMoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteMoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
