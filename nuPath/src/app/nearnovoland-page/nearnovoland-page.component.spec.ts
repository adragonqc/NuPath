import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NearNovolandPageComponent } from './nearnovoland-page.component';

describe('NearNovolandPageComponent', () => {
  let component: NearNovolandPageComponent;
  let fixture: ComponentFixture<NearNovolandPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NearNovolandPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NearNovolandPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
