import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AcademicPageComponent } from './academic-page.component';

describe('AcademicPageComponent', () => {
  let component: AcademicPageComponent;
  let fixture: ComponentFixture<AcademicPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AcademicPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AcademicPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
