import { TestBed } from '@angular/core/testing';

import { ExplorationService } from './exploration.service';

describe('ExplorationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ExplorationService = TestBed.get(ExplorationService);
    expect(service).toBeTruthy();
  });
});
