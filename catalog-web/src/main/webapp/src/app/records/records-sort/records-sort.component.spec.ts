import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecordsSortComponent } from './records-sort.component';

describe('RecordsSortComponent', () => {
  let component: RecordsSortComponent;
  let fixture: ComponentFixture<RecordsSortComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecordsSortComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecordsSortComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
