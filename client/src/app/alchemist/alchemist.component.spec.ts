import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlchemistComponent } from './alchemist.component';

describe('AlchemistComponent', () => {
  let component: AlchemistComponent;
  let fixture: ComponentFixture<AlchemistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlchemistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlchemistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
