import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NulifePageComponent } from './nulife-page.component';

describe('NulifePageComponent', () => {
  let component: NulifePageComponent;
  let fixture: ComponentFixture<NulifePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NulifePageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NulifePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
