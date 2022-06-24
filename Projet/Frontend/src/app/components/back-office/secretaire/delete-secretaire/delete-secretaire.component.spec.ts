import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteSecretaireComponent } from './delete-secretaire.component';

describe('DeleteSecretaireComponent', () => {
  let component: DeleteSecretaireComponent;
  let fixture: ComponentFixture<DeleteSecretaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteSecretaireComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteSecretaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
