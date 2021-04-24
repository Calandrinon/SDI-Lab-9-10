import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArtistsAddComponent } from './artists-add.component';

describe('ArtistsAddComponent', () => {
  let component: ArtistsAddComponent;
  let fixture: ComponentFixture<ArtistsAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArtistsAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArtistsAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
