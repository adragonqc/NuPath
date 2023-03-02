import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CatalystPageComponent } from './catalyst-page.component';

describe('CatalystPageComponent', () => {
  let component: CatalystPageComponent;
  let fixture: ComponentFixture<CatalystPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CatalystPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CatalystPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
