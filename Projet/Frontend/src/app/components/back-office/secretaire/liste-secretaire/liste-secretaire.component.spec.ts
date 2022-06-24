import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeSecretaireComponent } from './liste-secretaire.component';

describe('ListeSecretaireComponent', () => {
  let component: ListeSecretaireComponent;
  let fixture: ComponentFixture<ListeSecretaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListeSecretaireComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListeSecretaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
