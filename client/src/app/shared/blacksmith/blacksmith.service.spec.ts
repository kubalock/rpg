import { TestBed } from '@angular/core/testing';

import { BlacksmithService } from './blacksmith.service';

describe('BlacksmithService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BlacksmithService = TestBed.get(BlacksmithService);
    expect(service).toBeTruthy();
  });
});
