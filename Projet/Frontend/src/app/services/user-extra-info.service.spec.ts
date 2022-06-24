import { TestBed } from '@angular/core/testing';

import { UserExtraInfoService } from './user-extra-info.service';

describe('UserExtraInfoService', () => {
  let service: UserExtraInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserExtraInfoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
