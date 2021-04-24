import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecordsUpdateComponent } from './records-update.component';

describe('RecordsUpdateComponent', () => {
  let component: RecordsUpdateComponent;
  let fixture: ComponentFixture<RecordsUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecordsUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecordsUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
