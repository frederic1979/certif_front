import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreationMatrixComponent } from './creation-matrix.component';

describe('CreationMatrixComponent', () => {
  let component: CreationMatrixComponent;
  let fixture: ComponentFixture<CreationMatrixComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreationMatrixComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreationMatrixComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
