import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecordsFilterComponent } from './records-filter.component';

describe('RecordsFilterComponent', () => {
  let component: RecordsFilterComponent;
  let fixture: ComponentFixture<RecordsFilterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecordsFilterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecordsFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
