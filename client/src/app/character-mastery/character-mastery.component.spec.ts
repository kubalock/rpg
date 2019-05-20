import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CharacterMasteryComponent } from './character-mastery.component';

describe('CharacterMasteryComponent', () => {
  let component: CharacterMasteryComponent;
  let fixture: ComponentFixture<CharacterMasteryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CharacterMasteryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CharacterMasteryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
