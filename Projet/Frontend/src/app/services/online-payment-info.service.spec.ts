import { TestBed } from '@angular/core/testing';

import { OnlinePaymentInfoService } from './online-payment-info.service';

describe('OnlinePaymentInfoService', () => {
  let service: OnlinePaymentInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OnlinePaymentInfoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
