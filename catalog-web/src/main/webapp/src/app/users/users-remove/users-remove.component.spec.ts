import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersRemoveComponent } from './users-remove.component';

describe('UsersRemoveComponent', () => {
  let component: UsersRemoveComponent;
  let fixture: ComponentFixture<UsersRemoveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UsersRemoveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UsersRemoveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
