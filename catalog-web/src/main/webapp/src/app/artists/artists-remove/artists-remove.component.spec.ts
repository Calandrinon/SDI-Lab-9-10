import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtistsRemoveComponent } from './artists-remove.component';

describe('ArtistsRemoveComponent', () => {
  let component: ArtistsRemoveComponent;
  let fixture: ComponentFixture<ArtistsRemoveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArtistsRemoveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArtistsRemoveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
