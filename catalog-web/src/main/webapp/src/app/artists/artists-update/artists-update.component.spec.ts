import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtistsUpdateComponent } from './artists-update.component';

describe('ArtistsUpdateComponent', () => {
  let component: ArtistsUpdateComponent;
  let fixture: ComponentFixture<ArtistsUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArtistsUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArtistsUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
