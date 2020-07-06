import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryOnboardComponent } from './delivery-onboard.component';

describe('DeliveryOnboardComponent', () => {
  let component: DeliveryOnboardComponent;
  let fixture: ComponentFixture<DeliveryOnboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeliveryOnboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeliveryOnboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
