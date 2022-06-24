import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateSecretaireComponent } from './update-secretaire.component';

describe('UpdateSecretaireComponent', () => {
  let component: UpdateSecretaireComponent;
  let fixture: ComponentFixture<UpdateSecretaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateSecretaireComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateSecretaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
