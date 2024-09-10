import { TestBed } from '@angular/core/testing';

import { NrtmService } from './nrtm.service';

describe('NrtmService', () => {
  let service: NrtmService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NrtmService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
