import { TestBed } from '@angular/core/testing';

import { MedicalOfficeStaffService } from './medical-office-staff.service';

describe('MedicalOfficeStaffService', () => {
  let service: MedicalOfficeStaffService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicalOfficeStaffService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
