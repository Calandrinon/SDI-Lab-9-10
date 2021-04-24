import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecordsRemoveComponent } from './records-remove.component';

describe('RecordsRemoveComponent', () => {
  let component: RecordsRemoveComponent;
  let fixture: ComponentFixture<RecordsRemoveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecordsRemoveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecordsRemoveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
