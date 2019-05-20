import { TestBed } from '@angular/core/testing';

import { MasteryService } from './mastery.service';

describe('MasteryService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MasteryService = TestBed.get(MasteryService);
    expect(service).toBeTruthy();
  });
});
